package com.spring.cloud.moudle.study.demo.jvm;

/**
 * 对象重写finalize() escape GC
 * @author wangmj
 * @since 2019/1/28
 */
public class FinalizeEscapeGc {
    public static FinalizeEscapeGc HOOK = null;

    public void isAlive() {
        System.out.println("i am alive");
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalize executed start");
        super.finalize();
        FinalizeEscapeGc.HOOK = this;
        System.out.println("finalize executed end");
    }

    public static void main(String[] args) throws InterruptedException {
        HOOK = new FinalizeEscapeGc();
        //对象没有可达性时，只有一次escape机会，
        HOOK = null;
        //会首先判断对象是否有必要执行finalize方法
        //当对象没有覆盖finalize()方法或者对象没有调用过finalize()方法则认为没有必要执行
        System.gc();
        Thread.sleep(500);
        //由于覆盖了finalize()方法并且第一次调用，所有对象成功escape
        if (HOOK != null) {
            HOOK.isAlive();
        }else {
            System.out.println("i am dead");
        }


        HOOK = null;
        System.gc();
        Thread.sleep(500);
        //对象已经调用一次，则不会再次调用finalize
        if (HOOK != null) {
            HOOK.isAlive();
        }else {
            System.out.println("i am dead");
        }
    }
}
