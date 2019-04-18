package com.spring.cloud.moudle.study.demo.decodeDemo;

import lombok.Data;

/**
 * Java 对象抽象类
 *
 * @author wangmj
 * @since 2018/11/11
 */
@Data
public abstract class Packet {
    private final int version = 1;

    public abstract Byte getCommand();
}
