package org.microservice.monitoring.services.app.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.hzero.boot.message.entity.Message;
import org.hzero.mybatis.domian.Condition;
import org.hzero.mybatis.util.Sqls;
import org.microservice.monitoring.services.app.service.ESService;
import org.microservice.monitoring.services.app.service.MessageService;
import org.microservice.monitoring.services.domain.entity.*;
import org.microservice.monitoring.services.domain.repository.LogsAnalysisRepository;
import org.microservice.monitoring.services.domain.repository.LogsRepository;
import org.microservice.monitoring.services.domain.repository.WarningHistoryRepository;
import org.microservice.monitoring.services.domain.repository.WarningTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @description: ESService实现类
 * @author: miaoyang.chen@hand-china.com 2020-04-22 13:39
 **/
@Slf4j
@Service
public class ESServiceImpl implements ESService {

    @Autowired
    private LogsRepository logsRepository;

    @Autowired
    private MessageService messageService;

    @Autowired
    private WarningHistoryRepository warningHistoryRepository;

    @Autowired
    private WarningTypeRepository warningTypeRepository;

    @Autowired
    private LogsAnalysisRepository logsAnalysisRepository;

    @Override
    public void syncData(Iterable<ESModel> esModels) {
        esModels.forEach(val -> {
            List<Logs> logs = logsRepository.selectByCondition(Condition.builder(Logs.class)
                    .andWhere(Sqls.custom().andEqualTo(Logs.FIELD_LOG_ID, val.getId())).build());

            // 匹配邮箱预警是否打开
            WarningType warningType1 = new WarningType();
            warningType1.setType("邮箱预警");
            WarningType emailWarningType = warningTypeRepository.selectOne(warningType1);
            if (emailWarningType.getStatus().equals("1")) {
                // 邮件预警
                emailWarning(val.getMessage());
            }

            // 匹配手机短信预警是否打开
            WarningType warningType2 = new WarningType();
            warningType2.setType("手机短信预警");
            WarningType phoneWarningType = warningTypeRepository.selectOne(warningType2);
            if (phoneWarningType.getStatus().equals("1")) {
                // 手机短信预警
                phoneWarning(val.getMessage());
            }

            // 匹配微信预警是否打开
            WarningType warningType3 = new WarningType();
            warningType3.setType("微信预警");
            WarningType wechatWarningType = warningTypeRepository.selectOne(warningType3);
            if (wechatWarningType.getStatus().equals("1")) {
                // 微信预警
                wechatWarning(val.getMessage());
            }

            // 处理日志信息
            if (logs.size() != 0) {
                Logs newLog = logs.get(0);
                newLog.setLogHost(val.getHost());
                newLog.setLogMessage(val.getMessage());
                logsRepository.updateByPrimaryKeySelective(newLog);
                dataAnalysisHandle(val, "update");
            } else {
                Logs insertLog = new Logs();
                insertLog.setLogId(val.getId());
                insertLog.setLogHost(val.getHost());
                insertLog.setLogMessage(val.getMessage());
                logsRepository.insert(insertLog);
                dataAnalysisHandle(val, "insert");
            }
        });
    }

    public void emailWarning(String message) {
        if (message.indexOf("Caused by: java.net.BindException: 地址已在使用") != -1) {
            // 邮件预警
            Message emailResult = messageService.sendEmail();
            if (emailResult.getMessageId() != null) {
                WarningHistory warningHistory = new WarningHistory();
                warningHistory.setWarningType("邮件预警");
                warningHistory.setWarningTitle(emailResult.getSubject());
                warningHistory.setWarningContent(emailResult.getContent());
                warningHistory.setWarningStatus(emailResult.getSendFlag());
                warningHistory.setWarningRecipient("miaoyang.chen@hand-china.com");
                warningHistory.setWarningSender("logic_c@163.com");
                warningHistoryRepository.insert(warningHistory);
            }
            log.info("emailResult: {}", emailResult);
        }
    }

    public void phoneWarning(String message) {
        if (message.indexOf("Caused by: java.net.BindException: 地址已在使用") != -1) {
            // 手机短信预警
            Message phoneResult = messageService.sendPhone();
            log.info("phoneResult-1: {}", phoneResult);
            if (phoneResult.getMessageId() != null) {
                WarningHistory warningHistory = new WarningHistory();
                warningHistory.setWarningType("手机短信预警");
                warningHistory.setWarningTitle(phoneResult.getSubject());
                warningHistory.setWarningContent(phoneResult.getContent());
                warningHistory.setWarningStatus(phoneResult.getSendFlag());
                warningHistory.setWarningRecipient("18697701660");
                warningHistory.setWarningSender("Admin");
                warningHistoryRepository.insert(warningHistory);
            }
            log.info("phoneResult-2: {}", phoneResult);
        }
    }


    public void wechatWarning(String message) {
        if (message.indexOf("Caused by: java.net.BindException: 地址已在使用") != -1) {
            // 微信预警
            Map<String, Object> objectMap = messageService.sendWeChat();
            WarningHistory warningHistory = new WarningHistory();
            warningHistory.setWarningType("微信预警");
            warningHistory.setWarningTitle("微信预警-" + objectMap.get("touser"));
            warningHistory.setWarningContent("" + objectMap.get("content"));
            String errcode = (String)objectMap.get("errcode");
            warningHistory.setWarningStatus(errcode.equals("0") ? 1 : 0);
            warningHistory.setWarningRecipient("" + objectMap.get("touser"));
            warningHistory.setWarningSender("Admin");
            warningHistoryRepository.insert(warningHistory);
            log.info("wechatResult: {}", objectMap);
        }
    }


    public void dataAnalysisHandle(ESModel esModel, String type) {
        if (type.equals("update")) {
            List<LogsAnalysis> logsAnalyses = logsAnalysisRepository.selectByCondition(Condition.builder(LogsAnalysis.class).andWhere(Sqls.custom().andEqualTo(LogsAnalysis.FIELD_LOG_ID, esModel.getId())).build());
            LogsAnalysis logsAnalysis = logsAnalyses.get(0);
            logsAnalysis.setLogHost(esModel.getHost());
            logsAnalysis.setLogCagetory(".log");
            logsAnalysis.setLogLevel(esModel.getMessage().indexOf("Error") != -1 ? "一级" : (esModel.getMessage().indexOf("Exception") != -1 ? "二级" : "三级"));
            logsAnalysisRepository.updateByPrimaryKeySelective(logsAnalysis);
        } else {
            LogsAnalysis logsAnalysis = new LogsAnalysis();
            logsAnalysis.setLogId(esModel.getId());
            logsAnalysis.setLogHost(esModel.getHost());
            logsAnalysis.setLogCagetory(".log");
            logsAnalysis.setLogLevel(esModel.getMessage().indexOf("Error") != -1 ? "一级" : (esModel.getMessage().indexOf("Exception") != -1 ? "二级" : "三级"));
            logsAnalysisRepository.insert(logsAnalysis);
        }


    }

}
