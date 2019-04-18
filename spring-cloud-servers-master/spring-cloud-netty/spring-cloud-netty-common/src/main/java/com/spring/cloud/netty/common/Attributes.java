package com.spring.cloud.netty.common;

import io.netty.util.AttributeKey;

/**
 * @author wangmj
 * @since 2018/11/13
 */
public interface Attributes {
    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");
}
