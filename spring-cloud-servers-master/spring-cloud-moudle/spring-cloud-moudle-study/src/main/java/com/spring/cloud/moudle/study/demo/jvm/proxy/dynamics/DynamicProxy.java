package com.spring.cloud.moudle.study.demo.jvm.proxy.dynamics;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author wangmj
 * @since 2019/2/20
 */
public class DynamicProxy implements InvocationHandler {

    private Object target;

    public Object bind(Object target) {
        this.target = target;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        System.out.println("开始前");
        result = method.invoke(target, args);
        System.out.println("开始后");
        return result;
    }
}
