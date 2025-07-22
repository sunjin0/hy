package com.hy.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.hy.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Member"实体类"}
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("Member")
@TableName("user_member")
public class Member extends BaseEntity {

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String name;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String username;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    private String password;

    /**
     * 电话
     */
    @ApiModelProperty(value = "电话")
    private String phone;

    /**
     * 邮件
     */
    @ApiModelProperty(value = "邮件")
    private String email;

    /**
     * 头像
     */
    @ApiModelProperty(value = "头像")
    private String avatar;

}
