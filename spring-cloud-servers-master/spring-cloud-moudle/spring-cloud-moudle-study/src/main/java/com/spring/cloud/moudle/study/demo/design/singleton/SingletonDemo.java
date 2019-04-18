package com.spring.cloud.moudle.study.demo.design.singleton;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 单例模式
 *
 * @author wangmj
 * @since 2018/11/25
 */
public class SingletonDemo {
    private SingletonDemo() {

    }

    private  static SingletonDemo instance;

    public static SingletonDemo getInstance() {
        if (instance == null) {
            synchronized (SingletonDemo.class) {
                if (instance == null) {
                    instance = new SingletonDemo();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) throws InterruptedException {
//        Set set = Collections.synchronizedSet(new HashSet<>());
        Set set = new HashSet();
        List list = new ArrayList();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 100; j++) {
                        SingletonDemo instance = SingletonDemo.getInstance();
                    }
                }
            });
            threads.add(thread);
        }
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        System.out.println(list.size());
    }
}
