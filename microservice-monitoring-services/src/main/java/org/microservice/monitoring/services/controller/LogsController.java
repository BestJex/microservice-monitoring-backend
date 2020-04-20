package org.microservice.monitoring.services.controller;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.microservice.monitoring.services.domain.SearchModel;
import org.microservice.monitoring.services.repository.ESRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @description: 日志信息 Controller
 * @author: miaoyang.chen@hand-china.com 2020-04-20 15:26
 **/
@Slf4j
@RestController
@RequestMapping("/logs")
public class LogsController {


     @Autowired
     private ESRepository esRepository;

     // @Autowired
     // private ElasticsearchTemplate elasticsearchTemplate;


    @GetMapping("/all")
    public Iterable<SearchModel> getAll() {
        Iterable<SearchModel> all = esRepository.findAll();
        all.forEach(val -> {
            log.info("logs info: {}", JSON.toJSONString(val));
        });
        return all;
    }

}
