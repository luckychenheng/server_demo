package com.spring.cloud.netty.server.cache;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wangmj
 * @since 2018/11/14
 */
public class ServerConnectionMap {

    public static ConcurrentHashMap<String, String> loginConnection = new ConcurrentHashMap<>();

    public static void setLoginState(String state) {
        loginConnection.put("login", state);
    }

    public static String getLoginState(String userName) {
        return loginConnection.get(userName);
    }
}
