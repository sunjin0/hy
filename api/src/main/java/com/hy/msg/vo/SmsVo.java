package com.hy.msg.vo;

import  com.hy.msg.entity.Sms;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SmsVo extends Sms {
    private Long current;
    private Long pageSize;
}
