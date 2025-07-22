package com.hy.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hy.user.entity.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper extends BaseMapper<Member> {
}
