package org.microservice.monitoring.services.domain.repository;

import org.microservice.monitoring.services.domain.entity.ESModel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @description: ES资源库
 * @author: miaoyang.chen@hand-china.com
 * @create: 2020-04-22 12:53
 **/
public interface ESRepository extends ElasticsearchRepository<ESModel, String> {
}
