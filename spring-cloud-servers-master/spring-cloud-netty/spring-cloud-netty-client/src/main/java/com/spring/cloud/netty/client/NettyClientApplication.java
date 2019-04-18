package com.spring.cloud.netty.client;

import com.spring.cloud.auth.client.EnableAceAuthClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author wangmj
 * @since 2018/11/7
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableAutoConfiguration
@EnableAceAuthClient
public class NettyClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(NettyClientApplication.class, args);
    }
}
