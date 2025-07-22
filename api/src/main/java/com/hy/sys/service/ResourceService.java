package com.hy.sys.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hy.sys.entity.Resource;
import com.hy.sys.vo.ResourceVo;


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

    /**
     * 保存
     *
     * @param resource    资源
     * @param createNodes 创建节点
     * @return 保存结果
     */
    boolean save(Resource resource, Boolean createNodes);
}
