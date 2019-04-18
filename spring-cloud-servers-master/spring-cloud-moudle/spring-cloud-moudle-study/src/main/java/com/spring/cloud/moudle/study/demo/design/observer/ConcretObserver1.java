package com.spring.cloud.moudle.study.demo.design.observer;

/**
 * @author wangmj
 * @since 2019/1/7
 */
public class ConcretObserver1 implements Observer {
    @Override
    public void update() {
        System.out.println("observer1 update success");
    }
}
