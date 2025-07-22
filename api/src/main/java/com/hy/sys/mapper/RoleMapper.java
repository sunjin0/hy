package com.hy.sys.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hy.sys.entity.Role;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author sun
 * @since 2024-11-12
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {
}
