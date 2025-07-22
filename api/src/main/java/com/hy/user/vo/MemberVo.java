package com.hy.user.vo;

import  com.hy.user.entity.Member;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class MemberVo extends Member {
    private Long current;
    private Long pageSize;
}
