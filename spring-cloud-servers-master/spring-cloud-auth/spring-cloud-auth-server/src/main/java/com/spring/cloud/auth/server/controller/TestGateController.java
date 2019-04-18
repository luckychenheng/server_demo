package com.spring.cloud.auth.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangmj
 * @since 2018/10/25
 */
@RestController
@RequestMapping(value = "testGate")
public class TestGateController {
    @GetMapping(value = "getAuth")
    public String getAuth() {
        return "auth";
    }
}
