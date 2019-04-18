package com.spring.cloud.moudle.study.demo.concurency.wait;

import java.util.LinkedList;

/**
 * @author wangmj
 * @since 2019/1/7
 */
public class QueueDemo {
    private final static int MAX_SIZE = 10;

    private final LinkedList list = new LinkedList();

    public void product(int num) {
        synchronized (list) {
            while (list.size() + num > MAX_SIZE) {
                System.out.println("要生产数量"+num+",库存量:"+list.size()+",停止生产");
                try {
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            for (int i = 0; i < num; i++) {
                list.add(new Object());
            }

            System.out.println("生产数量"+num+",库存数量"+list.size());

            list.notifyAll();
        }
    }

    public void consume(int num) throws InterruptedException {
        synchronized (list) {
            while (list.size() < num) {
                System.out.println("要消费的数量:" + num + ",库存量:" + list.size() + ",停止消费");
                list.wait();
            }

            for (int i = 0; i < num; i++) {
                list.remove();
            }

            System.out.println("生产数量"+num+",库存数量"+list.size());
            list.notifyAll();
        }
    }

}
