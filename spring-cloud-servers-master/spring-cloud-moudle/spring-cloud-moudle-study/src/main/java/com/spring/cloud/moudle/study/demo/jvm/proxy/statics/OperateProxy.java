package com.spring.cloud.moudle.study.demo.jvm.proxy.statics;

/**
 * 代理类
 * @author wangmj
 * @since 2019/2/20
 */
public class OperateProxy {
    private Operate operate;

    public OperateProxy(Operate operate) {
        this.operate = operate;
    }

    public void proxyOperate() {
        System.out.println("操作之前");
        operate.operate();
        System.out.println("操作之后");
    }
}
