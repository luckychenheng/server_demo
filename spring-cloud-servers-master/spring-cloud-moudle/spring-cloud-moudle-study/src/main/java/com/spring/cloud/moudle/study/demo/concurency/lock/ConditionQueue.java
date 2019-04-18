package com.spring.cloud.moudle.study.demo.concurency.lock;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 利用condition实现一个阻塞队列
 *
 * @author wangmj
 * @since 2019/1/20
 */
public class ConditionQueue {

    private final int MAX_SIZE = 8;
    private List list = new LinkedList();

    private Lock lock = new ReentrantLock(true);
    private Condition fullCondition = lock.newCondition();
    private Condition emptyCondition = lock.newCondition();

    public void put(Object object) {
        lock.lock();
        try {
            if (list.size() > MAX_SIZE) {
                System.out.println("队列已满，线程阻塞");
                fullCondition.await();
            }
            list.add(object);
            System.out.println("通知线程可以取元素");
            emptyCondition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        } finally {
            lock.unlock();
        }
    }

    public Object take() {
        lock.lock();
        try {
            if (list.size() == 0) {
                System.out.println("队列为空，线程阻塞");
                emptyCondition.await();
            }
            Object obj = list.get(0);
            list.remove(0);
            System.out.println("通知线程可以进入队列");
            fullCondition.signal();
            return obj;
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        } finally {
            lock.unlock();
        }
    }

}
