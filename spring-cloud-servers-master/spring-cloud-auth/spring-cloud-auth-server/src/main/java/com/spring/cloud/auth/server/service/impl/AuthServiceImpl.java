package com.spring.cloud.auth.server.service.impl;

import com.spring.cloud.auth.common.util.jwt.JWTInfo;
import com.spring.cloud.auth.server.service.AuthService;
import com.spring.cloud.auth.server.util.JwtAuthenticationRequest;
import com.spring.cloud.auth.server.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author wangmj
 * @since 2018/10/27
 */
@Service
public class AuthServiceImpl implements AuthService {
    private final JwtTokenUtil jwtTokenUtil;
    @Value("${jwt.expire}")
    private int expire;

    @Autowired
    public AuthServiceImpl(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    public String login(JwtAuthenticationRequest authenticationRequest) throws Exception {
        //todo 查询数据库中用户信息
        return jwtTokenUtil.generateToken(new JWTInfo(authenticationRequest.getUsername(), 1L + "", "MR.WANG",expire));
    }

    @Override
    public String refresh(String oldToken) throws Exception {
        return jwtTokenUtil.generateToken(jwtTokenUtil.getInfoFromToken(oldToken));
    }
}
