package com.spring.cloud.netty.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author wangmj
 * @since 2018/11/4
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class NettyServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(NettyServerApplication.class, args);
    }
}
