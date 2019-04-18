package com.spring.cloud.moudle.rpc;

import com.spring.cloud.auth.client.EnableAceAuthClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author wangmj
 * @since 2018/11/3
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients({"com.spring.cloud.auth.client","com.spring.cloud.auth.service","com.spring.cloud.moudle.rpc"})
@EnableAutoConfiguration
@EnableAceAuthClient
public class MoudleRpcApplication {
    public static void main(String[] args) {
        SpringApplication.run(MoudleRpcApplication.class, args);
    }
}
