package org.microservice.monitoring.services.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @description: ElasticSearch实体类
 * @author: miaoyang.chen@hand-china.com 2020-04-20 15:37
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "integration_dev-demo-hello-*", type = "doc")
public class SearchModel {

    @Id
    private String id;

    @Field(type = FieldType.Text)
    private String index;

    @Field(type = FieldType.Text)
    private String path;

    @Field(type = FieldType.Text)
    private String host;

    @Field(type = FieldType.Text)
    private String message;

    @Field(type = FieldType.Text)
    private String tags;
}
