package com.spring.cloud.moudle.study.demo.jvm.proxy.statics;

/**
 * 静态代理，缺点是每个接口都需要一个代理类
 *
 * @author wangmj
 * @since 2019/2/20
 */
public class Test {
    public static void main(String[] args) {
        Operate operate = new OperateImpl();
        OperateProxy operateProxy = new OperateProxy(operate);
        operateProxy.proxyOperate();
    }
}
