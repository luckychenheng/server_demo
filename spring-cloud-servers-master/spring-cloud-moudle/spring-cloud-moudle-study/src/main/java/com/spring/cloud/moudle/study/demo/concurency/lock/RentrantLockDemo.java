package com.spring.cloud.moudle.study.demo.concurency.lock;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 继承ReentrantLock，重写getQueuedThreads,将当前队列反转成正序
 *
 * @author wangmj
 * @since 2019/1/19
 */
public class RentrantLockDemo extends ReentrantLock {

    public RentrantLockDemo(boolean fair) {
        super(fair);
    }

    @Override
    public Collection<Thread> getQueuedThreads() {
        List list = new ArrayList(super.getQueuedThreads());
        Collections.reverse(list);
        return list;
    }
}
