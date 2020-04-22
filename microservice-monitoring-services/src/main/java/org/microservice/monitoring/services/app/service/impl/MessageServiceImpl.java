package org.microservice.monitoring.services.app.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.hzero.boot.message.MessageClient;
import org.hzero.boot.message.entity.Message;
import org.hzero.boot.message.entity.Receiver;
import org.hzero.boot.message.entity.WeChatFont;
import org.microservice.monitoring.services.app.service.MessageService;
import org.microservice.monitoring.services.domain.entity.AccessTokenResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 消息实现类
 * @author: miaoyang.chen@hand-china.com 2020-04-22 15:01
 **/
@Slf4j
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageClient messageClient;

    @Autowired
    @Qualifier(value = "remoteRestTemplate")
    private RestTemplate restTemplate;

    @Override
    public Message sendEmail() {
        // 租户id
        long tenantId = 0L;
        // 服务编码
        String serverCode = "24493";
        // 消息模板编码
        String messageTemplateCode = "HITF.HEALTH_CHECK";
        // 接收人组
        List<Receiver> receiverList = new ArrayList<>();
        receiverList.add(new Receiver().setEmail("miaoyang.chen@hand-china.com"));
        // 参数
        Map<String, String> maps = new HashMap<>();
        maps.put("processName", "您有一条新的预警消息");
        maps.put("processDescription", "服务器发生预警，请及时登录系统查看错误信息！！！");
        return messageClient.sendEmail(tenantId, serverCode, messageTemplateCode, receiverList, maps);
    }

    @Override
    public Message sendPhone() {
        // 服务编码
        String serverCode = "MSMS_CHEN_MIAO_YANG";
        // 消息模板编码
        String messageTemplateCode = "MSMS.WARNING_SMS";
        // 接收人组
        List<Receiver> receiverList = new ArrayList<>();
        receiverList.add(new Receiver().setPhone("18697701660"));
        // 参数
        Map<String, String> maps = new HashMap<>();
        // 随机生成五位数
        int code = (int) ((Math.random() * 9 + 1) * 10000);
        maps.put("code", "" + code);
        return messageClient.sendSms(serverCode, messageTemplateCode, receiverList, maps);
    }

    @Override
    public Map<String, Object> sendWeChat() {
        // 第一步，获取 access_token
        String appid = "wx02c42dbfedd53985";
        String secret = "f32a35a64b1dc7aa97bd138fd1221495";
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appid + "&secret=" + secret;
        ResponseEntity<AccessTokenResult> accessTokenResult = restTemplate.getForEntity(url, AccessTokenResult.class);
        log.info("accessTokenResult: {}", accessTokenResult);
        log.info("accessTokenResult-Body: {}", accessTokenResult.getBody());

        // 第二步，发送消息
        String sendUrl = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=" + accessTokenResult.getBody().getAccess_token();
        HashMap<String, Object> data = new HashMap<>();
        data.put("touser", "owRGkuM3XxZjrq7ztNOU7CYAoyH0");
        data.put("msgtype", "text");
        HashMap<String, String> text = new HashMap<>();
        text.put("content", "服务器发生预警，请及时登录系统查看错误信息！！！");
        data.put("text", text);
        log.info("第二次数据data：{}", data);
        ResponseEntity<AccessTokenResult> result = restTemplate.postForEntity(sendUrl, data, AccessTokenResult.class);
        log.info("第二次结果：{}", result);
        log.info("第二次结果-Body：{}", result.getBody());
        data.put("errcode", result.getBody().getErrcode());
        data.put("content", "服务器发生预警，请及时登录系统查看错误信息！！！");
        return data;
    }
}
