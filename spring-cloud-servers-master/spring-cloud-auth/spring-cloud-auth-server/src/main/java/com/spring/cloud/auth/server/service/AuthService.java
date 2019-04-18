package com.spring.cloud.auth.server.service;

import com.spring.cloud.auth.server.util.JwtAuthenticationRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

/**
 * @author wangmj
 * @since 2018/10/27
 */
@Service
public interface AuthService {
    String login(JwtAuthenticationRequest authenticationRequest) throws Exception;
    String refresh(String oldToken) throws Exception;
}
