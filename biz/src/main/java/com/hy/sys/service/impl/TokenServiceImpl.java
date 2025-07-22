package com.hy.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hy.entity.Token;
import com.hy.sys.mapper.TokenMapper;
import com.hy.sys.service.TokenService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 令牌表 服务实现类
 * </p>
 *
 * @author sun
 * @since 2024-11-27
 */
@Service
public class TokenServiceImpl extends ServiceImpl<TokenMapper, Token> implements TokenService {

}
