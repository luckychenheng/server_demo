package com.spring.cloud.moudle.rpc.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author wangmj
 * @since 2018/11/4
 */
@FeignClient(value = "spring-cloud-auth")
public interface AuthFeign {
    @RequestMapping(value = "/authToken/getToken", method = RequestMethod.POST)
    String getToken();
}
