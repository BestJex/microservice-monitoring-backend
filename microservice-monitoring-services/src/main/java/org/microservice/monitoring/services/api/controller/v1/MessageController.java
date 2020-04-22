package org.microservice.monitoring.services.api.controller.v1;

import io.choerodon.core.domain.Page;
import io.choerodon.core.iam.ResourceLevel;
import io.choerodon.mybatis.pagehelper.annotation.SortDefault;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;
import io.choerodon.mybatis.pagehelper.domain.Sort;
import io.choerodon.swagger.annotation.Permission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hzero.core.base.BaseController;
import org.hzero.core.util.Results;
import org.microservice.monitoring.services.app.service.MessageService;
import org.microservice.monitoring.services.config.SwaggerTags;
import org.microservice.monitoring.services.domain.entity.AccessTokenResult;
import org.microservice.monitoring.services.domain.entity.WarningHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @description: 消息Controller
 * @author: miaoyang.chen@hand-china.com 2020-04-22 18:01
 **/
@RestController("messageController.v1")
@RequestMapping("/v1/message")
@Api(tags = SwaggerTags.MESSAGECONTROLLER)
public class MessageController extends BaseController {


    @Autowired
    private MessageService messageService;

    @ApiOperation(value = "公众号消息发送")
    @Permission(level = ResourceLevel.SITE)
    @GetMapping
    public ResponseEntity<AccessTokenResult> sendWeChat() {
        messageService.sendWeChat();
        return Results.success();
    }
}
