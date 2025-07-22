package com.hy.sys.vo;

import com.hy.sys.entity.RoleResource;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class RoleResourceVo extends RoleResource {
    private List<String> resourceIds;
    private Long current;
    private Long pageSize;
}
