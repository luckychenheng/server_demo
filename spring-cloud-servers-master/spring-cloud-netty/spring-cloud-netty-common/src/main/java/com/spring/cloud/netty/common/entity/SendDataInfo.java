package com.spring.cloud.netty.common.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author wangmj
 * @since 2018/11/14
 */
@Data
public class SendDataInfo {
    private String userId;
    private String userName;
    private String content;
    private String state;
    private Date createdDate;

    @Override
    public String toString() {
        return "SendDataInfo{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", content='" + content + '\'' +
                ", state='" + state + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }
}
