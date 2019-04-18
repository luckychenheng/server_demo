package com.spring.cloud.moudle.study.demo.jvm;

/**
 * @author wangmj
 * @since 2019/1/29
 */
public class OldGCDemo {
    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) {
        byte[] allocation1;

        allocation1 = new byte[4 * _1MB];
    }
}
