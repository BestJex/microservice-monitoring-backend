package org.microservice.monitoring.services.app.service;

import org.microservice.monitoring.services.domain.entity.ESModel;

/**
 * @description: ESService
 * @author: miaoyang.chen@hand-china.com
 * @create: 2020-04-22 13:38
 **/
public interface ESService {

    public void syncData(Iterable<ESModel> esModels);
}
