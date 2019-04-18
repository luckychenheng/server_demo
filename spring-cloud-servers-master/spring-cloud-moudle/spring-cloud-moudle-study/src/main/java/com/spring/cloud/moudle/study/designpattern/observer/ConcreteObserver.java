package com.spring.cloud.moudle.study.designpattern.observer;

/**
 * @author wangmj
 * @since 2018/11/1
 */
public class ConcreteObserver extends Observer {
    private String name;
    private String observerStat;

    public ConcreteObserver(String name, String observerStat) {
        this.name = name;
        this.observerStat = observerStat;
    }

    @Override
    public void update() {
        System.out.println(name+observerStat);
    }
}
