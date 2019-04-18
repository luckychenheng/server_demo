package com.spring.cloud.netty.common.constant;

/**
 * 消息协议常量类
 *
 * @author wangmj
 * @since 2018/11/21
 */
public final class MessageDataConstant {
    public final static byte CMD_HEAD = 0x5B;//消息命令头
    public final static byte CMD_END = 0x5D;//消息命令尾
    public static final int MAGIC_DATA = 0x7F;//魔数
    public static final byte VERSION = 1;//版本号

    public static final int MIN_LENGTH = 13;//1 头指令+4 魔数+1 版本+2 指令+4 数据长度+1 尾标识
    public static final int MAX_LENGTH = 2048;

    public static final byte SUCCESS_CODE = 0x1A;
    public static final byte FAIL_CODE = 0x1A;
}
