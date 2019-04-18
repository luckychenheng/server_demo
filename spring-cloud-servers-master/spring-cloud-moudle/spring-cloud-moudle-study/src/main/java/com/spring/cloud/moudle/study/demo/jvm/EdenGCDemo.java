package com.spring.cloud.moudle.study.demo.jvm;

/**
 * @author wangmj
 * @since 2019/1/29
 */
public class EdenGCDemo {
    //-Xmx20M -Xms20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) {
        byte[] allocation1, allocation2, allocation3, allocation4;

        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        //此时发生minorGC
        allocation4 = new byte[4 * _1MB];
    }
}
