package org.microservice.monitoring.services.app.service;

import org.hzero.boot.message.entity.Message;

/**
 * @description: 消息Service
 * @author: miaoyang.chen@hand-china.com
 * @create: 2020-04-22 15:05
 **/
public interface MessageService {


    Message sendEmail();

    void sendPhone();

    void sendWeChat();
}
