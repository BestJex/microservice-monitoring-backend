package org.microservice.monitoring.services.domain.entity;


import lombok.Data;

/**
 * @description: 微信 AccessToken实体类
 * @author: miaoyang.chen@hand-china.com 2020-04-22 17:48
 **/
@Data
public class AccessTokenResult {

    private String access_token;
    private String expires_in;

    private String errcode;
    private String errmsg;
}
