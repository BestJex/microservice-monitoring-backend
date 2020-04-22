package org.microservice.monitoring.services.app.service.impl;

import org.hzero.mybatis.domian.Condition;
import org.hzero.mybatis.util.Sqls;
import org.microservice.monitoring.services.app.service.ESService;
import org.microservice.monitoring.services.domain.entity.ESModel;
import org.microservice.monitoring.services.domain.entity.Logs;
import org.microservice.monitoring.services.domain.repository.LogsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: ESService实现类
 * @author: miaoyang.chen@hand-china.com 2020-04-22 13:39
 **/
@Service
public class ESServiceImpl implements ESService {

    @Autowired
    private LogsRepository logsRepository;

    @Override
    public void syncData(Iterable<ESModel> esModels) {
        esModels.forEach(val -> {
            List<Logs> logs = logsRepository.selectByCondition(Condition.builder(Logs.class)
                    .andWhere(Sqls.custom().andEqualTo(Logs.FIELD_LOG_ID, val.getId())).build());
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
}
