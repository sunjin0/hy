package com.hy.sys.service;

import com.hy.sys.entity.Resource;
import com.hy.sys.vo.ResourceVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * <p>
 * 资源表 服务类
 * </p>
 *
 * @author sun
 * @since 2024-11-12
 */
public interface ResourceService extends IService<Resource> {

    Page<ResourceVo> list(ResourceVo resource);
}
