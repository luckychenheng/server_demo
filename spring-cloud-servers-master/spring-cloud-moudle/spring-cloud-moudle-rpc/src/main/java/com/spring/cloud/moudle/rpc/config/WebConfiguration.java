package com.spring.cloud.moudle.rpc.config;

import com.spring.cloud.api.handler.GlobalExceptionHandler;
import com.spring.cloud.auth.client.interceptor.UserAuthInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author ace
 * @date 2017/9/8
 */
@Configuration
@Primary
public class WebConfiguration implements WebMvcConfigurer {
    @Bean
    GlobalExceptionHandler getGlobalExceptionHandler() {
        return new GlobalExceptionHandler();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getUserAuthRestInterceptor()).
                addPathPatterns("/**");
    }


    @Bean
    UserAuthInterceptor getUserAuthRestInterceptor() {
        return new UserAuthInterceptor();
    }

    /**
     * 需要用户和服务认证判断的路径
     *
     * @return
     */
    private ArrayList<String> getIncludePathPatterns() {
        ArrayList<String> list = new ArrayList<>();
        String[] urls = {
                "/api/moudle/**"
        };
        Collections.addAll(list, urls);
        return list;
    }

}
