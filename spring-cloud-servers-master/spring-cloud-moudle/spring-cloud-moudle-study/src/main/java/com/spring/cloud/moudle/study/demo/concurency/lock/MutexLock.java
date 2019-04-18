package com.spring.cloud.moudle.study.demo.concurency.lock;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * 独占锁自定义实现
 *
 * @author wangmj
 * @since 2019/1/18
 */
public class MutexLock {
    //自定义内部类继承AQS
    //独占锁只需实现tryAcquire与tryRelease方法
    //共享锁需要实现tryAcquireShared与tryReleaseShared方法
    private static class Sync extends AbstractQueuedSynchronizer {
        @Override
        protected boolean tryAcquire(int arg) {
            //cas将state变为arg
            if (compareAndSetState(0, arg)) {
                //设置独占线程为当前线程
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }


        @Override
        protected boolean tryRelease(int arg) {
            //若状态值为0，则说明锁已释放
            if (getState() == 0)
                throw new IllegalMonitorStateException();
            //设置独占线程为空，并将state更新为0
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }
    }

    private final Sync sync = new Sync();

    public void lock() {
        sync.acquire(1);
    }

    public void unlock() {
        sync.release(1);
    }


}
