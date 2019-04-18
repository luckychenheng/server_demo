package com.spring.cloud.auth.server.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangmj
 * @since 2018/10/25
 */
@RestController
@RequestMapping(value = "authToken")
public class TokenController {
    @PostMapping(value = "getToken")
    public String getToken() {
        return "token Ignore success11";
    }
}
