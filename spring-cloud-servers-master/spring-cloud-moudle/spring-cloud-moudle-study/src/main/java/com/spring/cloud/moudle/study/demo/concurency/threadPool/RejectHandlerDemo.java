package com.spring.cloud.moudle.study.demo.concurency.threadPool;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author wangmj
 * @since 2019/1/5
 */
public class RejectHandlerDemo implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println("拒绝任务");
        executor.shutdown();
    }
}
