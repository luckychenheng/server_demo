package com.spring.cloud.netty.server.util.ByteBufDemo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

/**
 * netty数据传输结构练习
 *
 * @author wangmj
 * @since 2018/11/11
 */
public class ByteBufDemo {
    public static void main(String[] args) {
        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer(9, 100);

        buffer.writeInt(11);
        buffer.writeInt(222);
        buffer.writeInt(3);

        System.out.println("buffer.getInt(0)"+buffer.getInt(0));
        System.out.println("buffer.getInt(1)"+buffer.getInt(1));
        System.out.println("buffer.getInt(2)"+buffer.getInt(2));

        print("allocator Buffer(9,100)", buffer);

        buffer.writeBytes(new byte[]{1, 2, 3, 4});
        print("writeBytes(new byte[]{1, 2, 3, 4})",buffer);

        buffer.writeInt(12);
        print("writeInt(12)", buffer);

        buffer.writeBytes(new byte[]{10});
        print("writeBytes(new byte[]{10})",buffer);

        buffer.writeShort(2);
        print("writeShort(2)",buffer);

        // read 方法改变读指针
        byte[] dst = new byte[buffer.readableBytes()];
        buffer.readBytes(dst);
        print("readBytes(" + dst.length + ")", buffer);

        buffer.resetReaderIndex();
        print("resetReaderIndex",buffer);
        buffer.resetWriterIndex();
        print("resetWriterIndex",buffer);
    }

    private static void print(String action, ByteBuf buffer) {
        System.out.println("after ===========" + action + "============");
        System.out.println("capacity(): " + buffer.capacity());
        System.out.println("maxCapacity(): " + buffer.maxCapacity());
        System.out.println("readerIndex(): " + buffer.readerIndex());
        System.out.println("readableBytes(): " + buffer.readableBytes());
        System.out.println("isReadable(): " + buffer.isReadable());
        System.out.println("writerIndex(): " + buffer.writerIndex());
        System.out.println("writableBytes(): " + buffer.writableBytes());
        System.out.println("isWritable(): " + buffer.isWritable());
        System.out.println("maxWritableBytes(): " + buffer.maxWritableBytes());
        System.out.println();
    }
}
