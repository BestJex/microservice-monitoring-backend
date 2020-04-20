package org.microservice.monitoring.services.repository;

import org.microservice.monitoring.services.domain.SearchModel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @description: ElasticsearchRepository
 * @author: miaoyang.chen@hand-china.com 2020-04-20 15:33
 **/
public interface ESRepository extends ElasticsearchRepository<SearchModel, String> {

}
