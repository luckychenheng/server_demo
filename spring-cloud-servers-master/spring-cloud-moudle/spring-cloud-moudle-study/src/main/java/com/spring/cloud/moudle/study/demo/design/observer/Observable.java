package com.spring.cloud.moudle.study.demo.design.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 被观察者，当其变化时会通知观察者
 *
 * @author wangmj
 * @since 2019/1/7
 */
public class Observable {
    private List<Observer> observerList = new ArrayList<>();

    public void addObserver(Observer observer) {
        if (observer == null) {
            return;
        }
        observerList.add(observer);
    }

    public void notifyObserver() {
        for (Observer observer : observerList) {
            observer.update();
        }
    }

    public void change() {
        System.out.println("i have changed,notify the observers");
        notifyObserver();
    }

    public void removeObserver(Observer observer) {
        assert observer != null;
        observerList.remove(observer);
    }
}
