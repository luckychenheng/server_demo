package com.spring.cloud.moudle.study.demo.jvm.proxy.statics;

/**
 * 被代理具体实现
 * @author wangmj
 * @since 2019/2/20
 */
public class OperateImpl implements Operate {
    @Override
    public void operate() {
        System.out.println("开始操作");
    }
}
