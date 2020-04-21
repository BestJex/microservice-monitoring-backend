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

    @Autowired
    public SwaggerTags(Docket docket) {
        docket.tags(new Tag(EXAMPLE, "EXAMPLE 案例"));
        docket.tags(new Tag(WARNINGTYPE, "预警类型"));
    }
}
