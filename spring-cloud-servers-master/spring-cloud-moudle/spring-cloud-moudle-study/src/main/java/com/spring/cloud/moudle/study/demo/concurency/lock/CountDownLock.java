package com.spring.cloud.moudle.study.demo.concurency.lock;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * 自定义共享锁，继承AQS，重写tryAcquireShared/tryReleaseShared
 *
 * @author wangmj
 * @since 2019/1/18
 */
public class CountDownLock {
    private final class sync extends AbstractQueuedSynchronizer {
        //自旋CAS将state设置成剩余可进入线程数
        @Override
        protected int tryAcquireShared(int reduce) {
            for (; ; ) {
                //当前状态值
                int current = getState();
                //剩余可进入的线程
                int rest = current - reduce;
                //当rest<0将线程任务放入队列等待
                if (rest < 0 || compareAndSetState(current, rest)) {
                    return rest;
                }
            }
        }


        @Override
        protected boolean tryReleaseShared(int increment) {
            for (; ; ) {
                int current = getState();
                int newCount = current + increment;
                if (newCount > 2)
                    throw new IllegalArgumentException();
                if (compareAndSetState(current, newCount)) {
                    return true;
                }
            }
        }

        sync(int count) {
            if (count < 0)
                throw new IllegalMonitorStateException();
            setState(count);
        }
    }

    private final sync sync = new sync(2);

    public void lock() {
        sync.acquireShared(1);
    }

    public void unLock() {
        sync.releaseShared(1);
    }
}
