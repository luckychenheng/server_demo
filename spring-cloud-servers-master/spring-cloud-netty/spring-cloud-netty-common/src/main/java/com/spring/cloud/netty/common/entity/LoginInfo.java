package com.spring.cloud.netty.common.entity;

import lombok.Data;

/**
 * 登录用户实体类
 *
 * @author wangmj
 * @since 2018/11/13
 */
@Data
public class LoginInfo {

    private String userName;
    private String password;
    private String platform;
    private String version;
    private String userId;

    @Override
    public String toString() {
        return "LoginInfo{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", platform='" + platform + '\'' +
                ", version='" + version + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
