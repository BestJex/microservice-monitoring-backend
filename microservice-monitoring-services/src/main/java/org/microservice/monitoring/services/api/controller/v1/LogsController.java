package org.microservice.monitoring.services.api.controller.v1;

import io.swagger.annotations.Api;
import org.hzero.core.util.Results;
import org.hzero.core.base.BaseController;
import org.microservice.monitoring.services.config.SwaggerTags;
import org.microservice.monitoring.services.domain.entity.Logs;
import org.microservice.monitoring.services.domain.repository.LogsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.hzero.mybatis.helper.SecurityTokenHelper;

import io.choerodon.core.domain.Page;
import io.choerodon.core.iam.ResourceLevel;
import io.choerodon.mybatis.pagehelper.annotation.SortDefault;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;
import io.choerodon.mybatis.pagehelper.domain.Sort;
import io.choerodon.swagger.annotation.Permission;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

/**
 *  管理 API
 *
 * @author miaoyang.chen@hand-china.com 2020-04-22 13:30:06
 */
@RestController("logsSiteController.v1")
@RequestMapping("/v1/logss")
@Api(tags = SwaggerTags.LOGSCONTROLLER)
public class LogsController extends BaseController {

    @Autowired
    private LogsRepository logsRepository;

    @ApiOperation(value = "列表")
    @Permission(level = ResourceLevel.SITE)
    @GetMapping
    public ResponseEntity<Page<Logs>> list(Logs logs, @ApiIgnore @SortDefault(value = Logs.FIELD_ID,
            direction = Sort.Direction.DESC) PageRequest pageRequest) {
        Page<Logs> list = logsRepository.pageAndSort(pageRequest, logs);
        return Results.success(list);
    }

    @ApiOperation(value = "明细")
    @Permission(level = ResourceLevel.SITE)
    @GetMapping("/{id}")
    public ResponseEntity<Logs> detail(@PathVariable Long id) {
        Logs logs = logsRepository.selectByPrimaryKey(id);
        return Results.success(logs);
    }

    @ApiOperation(value = "创建")
    @Permission(level = ResourceLevel.SITE)
    @PostMapping
    public ResponseEntity<Logs> create(@RequestBody Logs logs) {
        validObject(logs);
        logsRepository.insertSelective(logs);
        return Results.success(logs);
    }

    @ApiOperation(value = "修改")
    @Permission(level = ResourceLevel.SITE)
    @PutMapping
    public ResponseEntity<Logs> update(@RequestBody Logs logs) {
        SecurityTokenHelper.validToken(logs);
        logsRepository.updateByPrimaryKeySelective(logs);
        return Results.success(logs);
    }

    @ApiOperation(value = "删除")
    @Permission(level = ResourceLevel.SITE)
    @DeleteMapping
    public ResponseEntity<?> remove(@RequestBody Logs logs) {
        SecurityTokenHelper.validToken(logs);
        logsRepository.deleteByPrimaryKey(logs);
        return Results.success();
    }

}
