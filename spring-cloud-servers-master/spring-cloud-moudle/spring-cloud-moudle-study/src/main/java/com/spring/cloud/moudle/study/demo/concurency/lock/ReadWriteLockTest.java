package com.spring.cloud.moudle.study.demo.concurency.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁例子
 *
 * @author wangmj
 * @since 2019/1/20
 */
public class ReadWriteLockTest {
    private ReadWriteLock lock = new ReentrantReadWriteLock();
    private Map cache = new HashMap();

    public Object getCace(String key) {
        lock.readLock().lock();
        try {
            System.out.println("读锁进行");
            Thread.sleep(500);
            return cache.get(key);
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new IllegalMonitorStateException();
        } finally {
            lock.readLock().unlock();
        }
    }

    public void put(String key, Object value) {
        lock.writeLock().lock();
        try {
            System.out.println("写锁进行");
            cache.put(key, value);
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReadWriteLockTest lockTest = new ReadWriteLockTest();
        for (int i = 0; i < 50; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    lockTest.getCace("111");
                }
            }).start();
        }
//        Thread.sleep(200);
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    lockTest.put("111",111);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }


    }

}
