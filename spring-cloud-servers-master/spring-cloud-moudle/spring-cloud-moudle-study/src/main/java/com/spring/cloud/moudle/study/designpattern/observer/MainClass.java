package com.spring.cloud.moudle.study.designpattern.observer;

/**
 * @author wangmj
 * @since 2018/11/1
 */
public class MainClass {
    public static void main(String[] args) {
        ConcreteSubject subject = new ConcreteSubject();
        subject.attach(new ConcreteObserver("a", "停止看球"));
        subject.attach(new ConcreteObserver("b", "停止看电视"));
        subject.notifyObserver();
    }
}
