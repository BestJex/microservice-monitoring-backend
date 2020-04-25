package org.microservice.monitoring.services.job;

import lombok.extern.slf4j.Slf4j;
import org.hzero.core.util.Results;
import org.microservice.monitoring.services.app.service.ESService;
import org.microservice.monitoring.services.domain.entity.ESModel;
import org.microservice.monitoring.services.domain.repository.ESRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @description: 同步定时任务
 * @author: miaoyang.chen@hand-china.com 2020-04-24 17:05
 **/
@Slf4j
@Configuration
@EnableScheduling
public class SaticScheduleTask {

    @Autowired
    private ESRepository esRepository;

    @Autowired
    private ESService esService;

    // 每三秒执行一次
    // @Scheduled(fixedRate = 1000 * 3)
    // 两个小时执行一次
    @Scheduled(fixedRate = 1000 * 60 * 60 * 2)
    private void syncSchedule() {
        log.info("触发了定时任务...");
//        Iterable<ESModel> all = esRepository.findAll();
//        esService.syncData(all);
    }
}
