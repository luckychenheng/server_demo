package com.spring.cloud.gateway.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author wangmj
 * @since 2018/10/28
 */
@FeignClient(value = "spring-cloud-auth")
public interface AuthServiceFeign {
    @RequestMapping(value = "/jwt/refresh", method = RequestMethod.POST)
    String refresh(@RequestParam("oldToken") String oldToken);
}
