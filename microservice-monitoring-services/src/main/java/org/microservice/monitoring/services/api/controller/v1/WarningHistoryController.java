package org.microservice.monitoring.services.api.controller.v1;

import io.swagger.annotations.Api;
import org.hzero.core.util.Results;
import org.hzero.core.base.BaseController;
import org.microservice.monitoring.services.config.SwaggerTags;
import org.microservice.monitoring.services.domain.entity.WarningHistory;
import org.microservice.monitoring.services.domain.repository.WarningHistoryRepository;
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
 * @author miaoyang.chen@hand-china.com 2020-04-21 22:30:54
 */
@RestController("warningHistorySiteController.v1")
@RequestMapping("/v1/warning-history")
@Api(tags = SwaggerTags.WARNINGHISTORY)
public class WarningHistoryController extends BaseController {

    @Autowired
    private WarningHistoryRepository warningHistoryRepository;

    @ApiOperation(value = "列表")
    @Permission(level = ResourceLevel.SITE)
    @GetMapping
    public ResponseEntity<Page<WarningHistory>> list(WarningHistory warningHistory, @ApiIgnore @SortDefault(value = WarningHistory.FIELD_ID,
            direction = Sort.Direction.DESC) PageRequest pageRequest) {
        Page<WarningHistory> list = warningHistoryRepository.pageAndSort(pageRequest, warningHistory);
        return Results.success(list);
    }

    @ApiOperation(value = "明细")
    @Permission(level = ResourceLevel.SITE)
    @GetMapping("/{id}")
    public ResponseEntity<WarningHistory> detail(@PathVariable Long id) {
        WarningHistory warningHistory = warningHistoryRepository.selectByPrimaryKey(id);
        return Results.success(warningHistory);
    }

    @ApiOperation(value = "创建")
    @Permission(level = ResourceLevel.SITE)
    @PostMapping
    public ResponseEntity<WarningHistory> create(@RequestBody WarningHistory warningHistory) {
        validObject(warningHistory);
        warningHistoryRepository.insertSelective(warningHistory);
        return Results.success(warningHistory);
    }

    @ApiOperation(value = "修改")
    @Permission(level = ResourceLevel.SITE)
    @PutMapping
    public ResponseEntity<WarningHistory> update(@RequestBody WarningHistory warningHistory) {
        SecurityTokenHelper.validToken(warningHistory);
        warningHistoryRepository.updateByPrimaryKeySelective(warningHistory);
        return Results.success(warningHistory);
    }

    @ApiOperation(value = "删除")
    @Permission(level = ResourceLevel.SITE)
    @DeleteMapping
    public ResponseEntity<?> remove(@RequestBody WarningHistory warningHistory) {
        SecurityTokenHelper.validToken(warningHistory);
        warningHistoryRepository.deleteByPrimaryKey(warningHistory);
        return Results.success();
    }

}
