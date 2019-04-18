package com.spring.cloud.moudle.study.demo.design.observer;

/**
 * @author wangmj
 * @since 2019/1/7
 */
public class Test {
    public static void main(String[] args) {
        Observable observable = new Observable();
        observable.addObserver(new ConcretObserver1());
        observable.addObserver(new ConcretObserver2());
        observable.change();
    }
}
