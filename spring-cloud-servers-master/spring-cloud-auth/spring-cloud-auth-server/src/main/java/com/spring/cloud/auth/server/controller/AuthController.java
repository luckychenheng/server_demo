package com.spring.cloud.auth.server.controller;

import com.spring.cloud.auth.server.service.AuthService;
import com.spring.cloud.auth.server.util.JwtAuthenticationRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangmj
 * @since 2018/10/27
 */
@RestController
@RequestMapping(value = "jwt")
@Slf4j
public class AuthController {

    @Autowired
    private AuthService authService;

    @RequestMapping(value = "token", method = RequestMethod.POST)
    public String createAuthenticationToken(
            @RequestBody JwtAuthenticationRequest authenticationRequest) throws Exception {
        log.info(authenticationRequest.getUsername() + " require logging...");
        final String token = authService.login(authenticationRequest);
        return token;
    }

    @RequestMapping(value = "refresh",method = RequestMethod.POST)
    public String refresh(String oldToken) throws Exception {
        return authService.refresh(oldToken);
    }
}
