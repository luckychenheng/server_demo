package com.spring.cloud.netty.common.util;

import com.spring.cloud.netty.common.constant.MessageDataConstant;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

/**
 * @author wangmj
 * @since 2018/11/13
 */
public class BuildByteBuf {

    static final int DEFAULT_INITIAL_CAPACITY = 256;
    static final int DEFAULT_MAX_CAPACITY = Integer.MAX_VALUE;

    private BuildByteBuf() {
    }

    /**
     * 构造传输byteBuf
     *
     * @param bytes 数据
     * @param cmd   指令
     * @return 传输byteBuf
     */
    public static ByteBuf build(byte[] bytes, short cmd) {
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.buffer(DEFAULT_INITIAL_CAPACITY, DEFAULT_MAX_CAPACITY);
        byteBuf.writeByte(MessageDataConstant.CMD_HEAD);//1头指令
        byteBuf.writeInt(MessageDataConstant.MAGIC_DATA);//4 魔数
        byteBuf.writeByte(MessageDataConstant.VERSION);//1 版本
        byteBuf.writeShort(cmd);//2 指令
        byteBuf.writeInt(bytes.length);//4 数据长度
        byteBuf.writeBytes(bytes);//数据
        byteBuf.writeByte(MessageDataConstant.CMD_END);//尾标识
        return byteBuf;
    }
}
