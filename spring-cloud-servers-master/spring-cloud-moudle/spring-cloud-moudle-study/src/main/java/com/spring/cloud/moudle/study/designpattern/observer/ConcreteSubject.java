package com.spring.cloud.moudle.study.designpattern.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 具体通知者
 *
 * @author wangmj
 * @since 2018/11/1
 */
public class ConcreteSubject implements Subject {
    private List<Observer> observers = new ArrayList<>();

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObserver() {
        for (Observer o : observers) {
            o.update();
        }
    }
}
