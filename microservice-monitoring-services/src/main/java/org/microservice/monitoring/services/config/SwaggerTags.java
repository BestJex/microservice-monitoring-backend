package org.microservice.monitoring.services.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.Tag;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Swagger Api 描述配置
 */
@Configuration
public class SwaggerTags {

    public static final String EXAMPLE = "Example";
    public static final String WARNINGTYPE = "WarningType";
    public static final String WARNINGHISTORY = "WarningHistory";
    public static final String ESCONTROLLER = "ESController";
    public static final String LOGSCONTROLLER = "LogsController";
    public static final String MESSAGECONTROLLER = "MessageController";

    @Autowired
    public SwaggerTags(Docket docket) {
        docket.tags(new Tag(EXAMPLE, "EXAMPLE 案例"));
        docket.tags(new Tag(WARNINGTYPE, "预警类型"));
        docket.tags(new Tag(WARNINGHISTORY, "预警历史"));
        docket.tags(new Tag(ESCONTROLLER, "ES信息"));
        docket.tags(new Tag(LOGSCONTROLLER, "日志信息"));
        docket.tags(new Tag(MESSAGECONTROLLER, "消息发送管理"));
    }
}
