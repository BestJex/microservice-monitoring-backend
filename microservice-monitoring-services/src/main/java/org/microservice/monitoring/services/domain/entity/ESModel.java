package org.microservice.monitoring.services.domain.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @description: ES实体
 * @author: miaoyang.chen@hand-china.com 2020-04-22 12:39
 **/
@Data
@Document(indexName = "integration_dev-demo-hello-*", type = "doc")
public class ESModel {

    @Id
    private String id;
    private String host;
    private String message;

}
