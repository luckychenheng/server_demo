package com.spring.cloud.moudle.study.demo.concurency.lock;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangmj
 * @since 2019/1/20
 */
public class ConditionQueueTest {
    static ConditionQueue queue = new ConditionQueue();
    private static List<Thread> putList = new ArrayList<>();
    private static List<Thread> takeList = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    queue.put(111);
                }
            });
            putList.add(thread);
        }

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    queue.take();
                }
            });
            takeList.add(thread);
        }
        for (Thread putThread : putList) {
            putThread.start();
        }

        Thread.sleep(2000);
        for (Thread takeThread : takeList) {
            takeThread.start();
        }
    }

}
