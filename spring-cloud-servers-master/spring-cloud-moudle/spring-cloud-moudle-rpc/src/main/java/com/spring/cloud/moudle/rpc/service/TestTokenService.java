package com.spring.cloud.moudle.rpc.service;

import com.spring.cloud.moudle.rpc.feign.AuthFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wangmj
 * @since 2018/11/4
 */
@Service
@Slf4j
public class TestTokenService {

    private final AuthFeign authFeign;

    @Autowired
    public TestTokenService(AuthFeign authFeign) {
        this.authFeign = authFeign;
    }

    public String getToken() {
        return authFeign.getToken();
    }
}
