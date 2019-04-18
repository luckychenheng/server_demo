package com.spring.cloud.moudle.study.demo.concurency.reentrantDemo;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wangmj
 * @since 2018/12/28
 */
public class ReentrantLockDemo {

    private volatile int a = 0;

    private ReentrantLock lock = new ReentrantLock();

    public void write() {
        lock.lock();
        try {
            a++;
        } finally {
            lock.unlock();
        }

    }

    public int get() {
        lock.lock();
        try {
            int i = a;
            return i;
        } finally {
            lock.unlock();
        }
    }

}
