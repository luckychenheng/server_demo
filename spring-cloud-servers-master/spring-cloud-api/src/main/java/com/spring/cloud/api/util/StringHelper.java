package com.spring.cloud.api.util;

/**
 * @author wangmj
 * @since 2018/10/27
 */
public class StringHelper {
    public static String getObjectValue(Object obj){
        return obj==null?"":obj.toString();
    }
}
