package com.spring.cloud.netty.common.util;

import com.spring.cloud.netty.common.Attributes;
import io.netty.channel.Channel;
import io.netty.util.Attribute;

/**
 * 登录校验公共类
 *
 * @author wangmj
 * @since 2018/11/13
 */
public class LoginUtil {
    public static void markAsLogin(Channel channel) {
        channel.attr(Attributes.LOGIN).set(true);
    }

    public static boolean hasLogin(Channel channel) {
        Attribute<Boolean> loginAttr = channel.attr(Attributes.LOGIN);
        return loginAttr.get() != null;
    }
}
