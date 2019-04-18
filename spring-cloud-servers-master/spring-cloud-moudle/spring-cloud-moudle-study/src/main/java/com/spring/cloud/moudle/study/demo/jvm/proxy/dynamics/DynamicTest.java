package com.spring.cloud.moudle.study.demo.jvm.proxy.dynamics;

/**
 * @author wangmj
 * @since 2019/2/20
 */
public class DynamicTest {
    public static void main(String[] args) {
        DynamicProxy dynamicProxy = new DynamicProxy();
        Operate bind = (Operate) dynamicProxy.bind(new OperateImpl());
        bind.operate();
    }
}
