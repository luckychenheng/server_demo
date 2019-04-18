package com.spring.cloud.moudle.study.designpattern.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 抽象通知者
 *
 * @author wangmj
 * @since 2018/11/1
 */
public interface Subject {
    void attach(Observer observer);

    void detach(Observer observer);

    void notifyObserver();

}
