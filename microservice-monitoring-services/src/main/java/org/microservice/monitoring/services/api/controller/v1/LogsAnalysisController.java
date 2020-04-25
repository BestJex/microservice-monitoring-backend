package org.microservice.monitoring.services.api.controller.v1;

import org.hzero.core.util.Results;
import org.hzero.core.base.BaseController;
import org.microservice.monitoring.services.domain.entity.LogsAnalysis;
import org.microservice.monitoring.services.domain.repository.LogsAnalysisRepository;
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
 * @author miaoyang.chen@hand-china.com 2020-04-23 20:44:11
 */
@RestController("logsAnalysisSiteController.v1")
@RequestMapping("/v1/logs-analysiss")
public class LogsAnalysisController extends BaseController {

    @Autowired
    private LogsAnalysisRepository logsAnalysisRepository;

    @ApiOperation(value = "列表")
    @Permission(level = ResourceLevel.SITE)
    @GetMapping
    public ResponseEntity<Page<LogsAnalysis>> list(LogsAnalysis logsAnalysis, @ApiIgnore @SortDefault(value = LogsAnalysis.FIELD_ID,
            direction = Sort.Direction.DESC) PageRequest pageRequest) {
        Page<LogsAnalysis> list = logsAnalysisRepository.pageAndSort(pageRequest, logsAnalysis);
        return Results.success(list);
    }

    @ApiOperation(value = "明细")
    @Permission(level = ResourceLevel.SITE)
    @GetMapping("/{id}")
    public ResponseEntity<LogsAnalysis> detail(@PathVariable Long id) {
        LogsAnalysis logsAnalysis = logsAnalysisRepository.selectByPrimaryKey(id);
        return Results.success(logsAnalysis);
    }

    @ApiOperation(value = "创建")
    @Permission(level = ResourceLevel.SITE)
    @PostMapping
    public ResponseEntity<LogsAnalysis> create(@RequestBody LogsAnalysis logsAnalysis) {
        validObject(logsAnalysis);
        logsAnalysisRepository.insertSelective(logsAnalysis);
        return Results.success(logsAnalysis);
    }

    @ApiOperation(value = "修改")
    @Permission(level = ResourceLevel.SITE)
    @PutMapping
    public ResponseEntity<LogsAnalysis> update(@RequestBody LogsAnalysis logsAnalysis) {
        SecurityTokenHelper.validToken(logsAnalysis);
        logsAnalysisRepository.updateByPrimaryKeySelective(logsAnalysis);
        return Results.success(logsAnalysis);
    }

    @ApiOperation(value = "删除")
    @Permission(level = ResourceLevel.SITE)
    @DeleteMapping
    public ResponseEntity<?> remove(@RequestBody LogsAnalysis logsAnalysis) {
        SecurityTokenHelper.validToken(logsAnalysis);
        logsAnalysisRepository.deleteByPrimaryKey(logsAnalysis);
        return Results.success();
    }

}
