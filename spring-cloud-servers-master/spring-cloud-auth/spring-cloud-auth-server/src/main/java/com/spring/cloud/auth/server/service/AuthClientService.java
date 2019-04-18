package com.spring.cloud.auth.server.service;


import java.util.List;

/**
 * Created by ace on 2017/9/10.
 */
public interface AuthClientService {
    public String apply(String clientId, String secret) throws Exception;
}
