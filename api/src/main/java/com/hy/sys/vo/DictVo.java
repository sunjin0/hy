package com.hy.sys.vo;

import com.hy.sys.entity.Dict;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class DictVo extends Dict {
    private String key;
    private List<DictVo> children;
    private Long current;
    private Long pageSize;
}
