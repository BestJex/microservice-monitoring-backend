package org.microservice.monitoring.services.api.controller.v1;

import io.swagger.annotations.Api;
import org.hzero.core.util.Results;
import org.hzero.core.base.BaseController;
import org.microservice.monitoring.services.config.SwaggerTags;
import org.microservice.monitoring.services.domain.entity.WarningType;
import org.microservice.monitoring.services.domain.repository.WarningTypeRepository;
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
 * @author miaoyang.chen@hand-china.com 2020-04-21 10:03:35
 */
@RestController("warningTypeController.v1")
@RequestMapping("/v1/warning-types")
@Api(tags = SwaggerTags.WARNINGTYPE)
public class WarningTypeController extends BaseController {

    @Autowired
    private WarningTypeRepository warningTypeRepository;

    @ApiOperation(value = "列表")
    @Permission(level = ResourceLevel.SITE)
    @GetMapping
    public ResponseEntity<Page<WarningType>> list(WarningType warningType, @ApiIgnore @SortDefault(value = WarningType.FIELD_ID,
            direction = Sort.Direction.DESC) PageRequest pageRequest) {
        Page<WarningType> list = warningTypeRepository.pageAndSort(pageRequest, warningType);
        return Results.success(list);
    }

    @ApiOperation(value = "明细")
    @Permission(level = ResourceLevel.SITE)
    @GetMapping("/{id}")
    public ResponseEntity<WarningType> detail(@PathVariable Long id) {
        WarningType warningType = warningTypeRepository.selectByPrimaryKey(id);
        return Results.success(warningType);
    }

    @ApiOperation(value = "创建")
    @Permission(level = ResourceLevel.SITE)
    @PostMapping
    public ResponseEntity<WarningType> create(@RequestBody WarningType warningType) {
        validObject(warningType);
        warningTypeRepository.insertSelective(warningType);
        return Results.success(warningType);
    }

    @ApiOperation(value = "修改")
    @Permission(level = ResourceLevel.SITE)
    @PutMapping
    public ResponseEntity<WarningType> update(@RequestBody WarningType warningType) {
        SecurityTokenHelper.validToken(warningType);
        warningTypeRepository.updateByPrimaryKeySelective(warningType);
        return Results.success(warningType);
    }

    @ApiOperation(value = "删除")
    @Permission(level = ResourceLevel.SITE)
    @DeleteMapping
    public ResponseEntity<?> remove(@RequestBody WarningType warningType) {
        SecurityTokenHelper.validToken(warningType);
        warningTypeRepository.deleteByPrimaryKey(warningType);
        return Results.success();
    }

}
