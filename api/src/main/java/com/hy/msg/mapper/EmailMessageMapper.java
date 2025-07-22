package com.hy.msg.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hy.msg.entity.Email;
import org.apache.ibatis.annotations.Mapper;

/**
 * 电子邮件消息映射器
 *
 * @author sun
 * @date 2024/09/11
 */
@Mapper
public interface EmailMessageMapper extends BaseMapper<Email> {

}
