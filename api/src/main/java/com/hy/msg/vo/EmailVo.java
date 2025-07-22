package com.hy.msg.vo;

import  com.hy.msg.entity.Email;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
public class EmailVo extends Email {
    private Long current;
    private Long pageSize;
}
