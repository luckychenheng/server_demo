package com.spring.cloud.moudle.study.demo.concurency.cas;

import com.spring.cloud.moudle.study.demo.concurency.lock.MutexLock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 原子操作
 *
 * @author wangmj
 * @since 2018/12/26
 */
public class Counter {

    private int i = 0;
    private AtomicInteger atomicI = new AtomicInteger(0);
    private MutexLock lock = new MutexLock();

    public static void main(String[] args) throws InterruptedException {
        long start = System.nanoTime();
        final Counter counter = new Counter();
        List<Thread> ts = new ArrayList<>();
        for (int j = 0; j < 10000; j++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int k = 0; k < 10000; k++) {
                        counter.unsafeCount();
//                        counter.safeCount();
                    }
                }
            });
            ts.add(t);
        }
        System.out.println(ts.size());
        for (Thread t : ts) {
            t.start();
        }
        //等待所有线程执行完成
        for (Thread t : ts) {
            t.join();
        }

        long endTime = System.nanoTime();

        System.out.println((endTime-start)/1000);

        System.out.println(counter.i);
        System.out.println(counter.atomicI.get());
    }


    private  void unsafeCount() {
        lock.lock();
        i++;
        lock.unlock();
    }

    private void safeCount() {
        while (true) {
            int i = atomicI.get();
            boolean compareAndSet = atomicI.compareAndSet(i, ++i);
            if (compareAndSet) {
                break;
            }
        }
    }
}
