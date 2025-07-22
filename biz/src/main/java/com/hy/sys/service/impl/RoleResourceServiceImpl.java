package com.hy.sys.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hy.sys.entity.RoleResource;
import com.hy.sys.mapper.RoleResourceMapper;
import com.hy.sys.service.RoleResourceService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色资源表 服务实现类
 * </p>
 *
 * @author sun
 * @since 2024-11-12
 */
@Service
public class RoleResourceServiceImpl extends ServiceImpl<RoleResourceMapper, RoleResource> implements RoleResourceService {

    @Override
    public List<String> getPermissionByRoleId(String roleId) {
        LambdaQueryWrapper<RoleResource> query = Wrappers.lambdaQuery(RoleResource.class);
        query.select(RoleResource::getResourceId)
                .eq(RoleResource::getRoleId, roleId);
        List<RoleResource> list = list(query);
        return list.stream().map(RoleResource::getResourceId).collect(Collectors.toList());
    }
}
