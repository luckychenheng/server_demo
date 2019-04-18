package com.spring.cloud.moudle.study.demo.concurency.threadPool;

import java.util.concurrent.*;

/**
 * @author wangmj
 * @since 2019/1/5
 */
public class ThreadPoolDemo {

    public static void main(String[] args) throws InterruptedException {
        RejectHandlerDemo rejectHandlerDemo = new RejectHandlerDemo();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 4, 500,
                TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(6),rejectHandlerDemo);
        TaskDemo taskDemo = new TaskDemo();
        for (int i = 0; i < 17; i++) {
            executor.execute(taskDemo);
        }
        Thread.sleep(1000);
        printExecutor(executor);
        executor.shutdown();
    }

    private static void printExecutor(ThreadPoolExecutor executor) {
        System.out.println("corePoolSize=" + executor.getCorePoolSize());
        System.out.println("poolSize=" + executor.getPoolSize());
        System.out.println("queueSize=" + executor.getQueue().size());
    }
}
