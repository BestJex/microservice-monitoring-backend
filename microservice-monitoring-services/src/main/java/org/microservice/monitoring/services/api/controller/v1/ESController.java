package org.microservice.monitoring.services.api.controller.v1;

import io.choerodon.core.domain.Page;
import io.choerodon.core.iam.ResourceLevel;
import io.choerodon.swagger.annotation.Permission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.hzero.core.util.Results;
import org.microservice.monitoring.services.app.service.ESService;
import org.microservice.monitoring.services.config.SwaggerTags;
import org.microservice.monitoring.services.domain.entity.ESModel;
import org.microservice.monitoring.services.domain.repository.ESRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.spring.web.json.Json;

/**
 * @description: ES Controller
 * @author: miaoyang.chen@hand-china.com 2020-04-22 12:36
 **/
@Slf4j
@RestController("esController.v1")
@RequestMapping("/v1/es-logs")
@Api(tags = SwaggerTags.ESCONTROLLER)
public class ESController {

    @Autowired
    private ESRepository esRepository;

    @Autowired
    private ESService esService;


    @ApiOperation(value = "同步ES数据到Mysql")
    @Permission(level = ResourceLevel.SITE)
    @GetMapping
    public ResponseEntity<ESModel> syncData() {
        Iterable<ESModel> all = esRepository.findAll();
        esService.syncData(all);
        return Results.success();
    }
}
