package com.hy.sys.controller;



import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hy.permission.Permission;
import com.hy.sys.service.ResourceService;
import com.hy.sys.service.RoleService;
import com.hy.entity.Option;
import com.hy.entity.WebResponse;
import com.hy.sys.entity.Role;
import com.hy.exception.ServerException;
import com.hy.i18n.I18nUtils;
import com.hy.validator.ValidEntity;
import com.hy.sys.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author sun
 * @since 2024-11-12
 */
@Api(tags = "系统角色服务 API")
@Validated
@RestController
@Permission(path = "/sys/role")
@RequestMapping("/api/sys/role")
public class RoleController {
    @Resource
    private RoleService roleService;

    @Resource
    private ResourceService resourceService;


    @ApiOperation(value = "角色列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "访问令牌", required = true, dataType = "string", paramType = "header")
    })
    @PostMapping("/list")
    public WebResponse<List<Role>> list(@RequestBody RoleVo role) throws ServerException {
        Page<Role> dictPage = new Page<>(role.getCurrent(), role.getPageSize());
        Wrapper<Role> queryWrapper = Wrappers.lambdaQuery(Role.class)
                .like(StringUtils.isNotBlank(role.getName()), Role::getName, role.getName())
                .like(StringUtils.isNotBlank(role.getDescription()), Role::getDescription, role.getDescription())
                .orderByDesc(Role::getCreatedAt);
        Page<Role> rolePage = roleService.page(dictPage, queryWrapper);
        return WebResponse.Page(rolePage.getRecords(), rolePage.getTotal());
    }

    @ApiOperation(value = "删除角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", required = true),
            @ApiImplicitParam(name = "Authorization", value = "访问令牌", required = true, dataType = "string", paramType = "header")
    })
    @Permission(path = "/sys/role", type = Permission.Type.Write)
    @GetMapping("/delete")
    public WebResponse<Boolean> delete(@RequestParam @NotBlank String id) throws ServerException {
        LambdaUpdateWrapper<Role> updateWrapper = Wrappers.lambdaUpdate(Role.class);
        updateWrapper
                .eq(Role::getId, id);
        boolean update = roleService.remove(updateWrapper);
        return WebResponse.OK(update ? I18nUtils.getMessage("delete.success") : I18nUtils.getMessage("delete.fail"), true);
    }

    @ApiOperation(value = "新增角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "访问令牌", required = true, dataType = "string", paramType = "header")
    })
    @Permission(path = "/sys/role", type = Permission.Type.Write)
    @PostMapping("/add")
    public WebResponse<Boolean> save(@RequestBody
                                     @ValidEntity(fieldNames = {"name"})
                                     Role role) throws ServerException {
        boolean save = roleService.save(role);
        return WebResponse.OK(save ? I18nUtils.getMessage("add.success") : I18nUtils.getMessage("add.fail"), true);
    }

    @ApiOperation(value = "修改角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "访问令牌", required = true, dataType = "string", paramType = "header")
    })
    @Permission(path = "/sys/role", type = Permission.Type.Write)
    @PostMapping("/update")
    public WebResponse<Boolean> update(@RequestBody
                                       @ValidEntity(fieldNames = {"name"})
                                       Role role) throws ServerException {
        boolean update = roleService.updateById(role);
        return WebResponse.OK(update ? I18nUtils.getMessage("update.success") : I18nUtils.getMessage("update.fail"), true);
    }

    @ApiOperation(value = "角色详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", required = true),
            @ApiImplicitParam(name = "Authorization", value = "访问令牌", required = true, dataType = "string", paramType = "header")
    })
    @GetMapping("/info")
    public WebResponse<Role> detail(@RequestParam @NotBlank String id) throws ServerException {
        Role role = roleService.getById(id);
        return WebResponse.OK(role);
    }

    @ApiOperation(value = "角色下拉框数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "访问令牌", required = true, dataType = "string", paramType = "header")
    })
    @GetMapping("/options")
    public WebResponse<List<Option>> options() throws ServerException {
        List<Option> options = roleService.lambdaQuery()
                .select(Role::getId, Role::getName)
                .list()
                .stream()
                .map(role -> new Option(role.getName(), role.getId()))
                .collect(Collectors.toList());
        return WebResponse.OK(options);
    }

    @ApiOperation(value = "资源")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "访问令牌", required = true, dataType = "string", paramType = "header")
    })
    @GetMapping("/resource")
    public WebResponse<List<ResourceVo>> resource() throws ServerException {
        ResourceVo resourceVo = new ResourceVo();
        resourceVo.setCurrent(1L);
        resourceVo.setPageSize(100000L);
        Page<ResourceVo> list = resourceService.list(resourceVo);
        return WebResponse.OK(list.getRecords());
    }


}
