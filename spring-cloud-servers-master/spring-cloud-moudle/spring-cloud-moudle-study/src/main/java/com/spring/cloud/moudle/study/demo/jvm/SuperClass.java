package com.spring.cloud.moudle.study.demo.jvm;

/**
 * @author wangmj
 * @since 2019/2/15
 */
public class SuperClass {
    public static int value = 22;
    static {
        System.out.println("super init");
    }
}
