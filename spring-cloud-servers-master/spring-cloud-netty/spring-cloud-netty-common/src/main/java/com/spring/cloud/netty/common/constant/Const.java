package com.spring.cloud.netty.common.constant;

/**
 * @author wangmj
 * @since 2018/11/12
 */
public final class Const {
    public static final short CONNECT_REQ = 0x1001;//登录请求
    public static final short CONNECT_RESP = 0x1002;//登录请求
    public static final short SEND_DATA = 0x1003;//发送数据
    public static final short HEART_BEAT = 0x2001;//心跳
    public static final byte HEART_BEAT_DATA = 0x4B;
}
