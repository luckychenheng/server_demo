package com.spring.cloud.moudle.study.demo.jvm;

/**
 * @author wangmj
 * @since 2019/2/15
 */
public class SubClass extends SuperClass {
//    public static int value = 11;
    static {
        System.out.println("sub init");
    }
}
