package org.microservice.monitoring.services.app.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.hzero.boot.message.entity.Message;
import org.hzero.mybatis.domian.Condition;
import org.hzero.mybatis.util.Sqls;
import org.microservice.monitoring.services.app.service.ESService;
import org.microservice.monitoring.services.app.service.MessageService;
import org.microservice.monitoring.services.domain.entity.ESModel;
import org.microservice.monitoring.services.domain.entity.Logs;
import org.microservice.monitoring.services.domain.entity.WarningHistory;
import org.microservice.monitoring.services.domain.repository.LogsRepository;
import org.microservice.monitoring.services.domain.repository.WarningHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public void syncData(Iterable<ESModel> esModels) {
        esModels.forEach(val -> {
            List<Logs> logs = logsRepository.selectByCondition(Condition.builder(Logs.class)
                    .andWhere(Sqls.custom().andEqualTo(Logs.FIELD_LOG_ID, val.getId())).build());
            warning(val.getMessage());
            if (logs.size() != 0) {
                Logs newLog = logs.get(0);
                newLog.setLogHost(val.getHost());
                newLog.setLogMessage(val.getMessage());
                logsRepository.updateByPrimaryKeySelective(newLog);
            } else {
                Logs insertLog = new Logs();
                insertLog.setLogId(val.getId());
                insertLog.setLogHost(val.getHost());
                insertLog.setLogMessage(val.getMessage());
                logsRepository.insert(insertLog);
            }
        });
    }

    public void warning(String message) {
        if (message.indexOf("Caused by: java.net.BindException: 地址已在使用") != -1) {
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

}
