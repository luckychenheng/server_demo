package com.spring.cloud.moudle.rpc.controller;

import com.spring.cloud.api.message.BaseResponse;
import com.spring.cloud.auth.client.annotation.IgnoreUserToken;
import com.spring.cloud.moudle.rpc.service.TestTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangmj
 * @since 2018/11/3
 */
@RestController
@RequestMapping(value = "token")
public class TestToken {

    @Autowired
    private TestTokenService tokenService;

    @PostMapping(value = "testIgnoreToken")
//    @IgnoreUserToken
    public String testIgnoreToken() {
        return tokenService.getToken();
    }
}
