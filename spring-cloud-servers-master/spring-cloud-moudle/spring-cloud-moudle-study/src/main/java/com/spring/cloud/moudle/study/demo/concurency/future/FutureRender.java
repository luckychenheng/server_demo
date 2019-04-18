package com.spring.cloud.moudle.study.demo.concurency.future;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * future带有返回结果的任务
 * future生命周期：尚未开始、正在运行、已完成 future.isDone() isCanceled() cancel(boolean mayInterruptIfRunning)
 * future.get会阻塞直到有返回结果
 * future.get异常：ExecutionException InterruptedException TimeoutException(timeout)
 *
 * @author wangmj
 * @create 2018/11/27
 */
public class FutureRender {

    private ExecutorService executorService;

    public FutureRender() {
        executorService = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), 4, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(6));
    }

    public void render() {
        Callable<List> callable = new Callable<List>() {
            @Override
            public List call() throws Exception {
                System.out.println("提前计算结果");
                List list = new ArrayList();
                list.add("111");
//                Thread.sleep(2000);//验证TimeoutException
                Thread.currentThread().interrupt();
//                int i = 1/0; //验证ExecutionException
                return list;
            }
        };
        //提前提交计算
        Future<List> future = executorService.submit(callable);
        renderText();
        try {
            List list = future.get(1, TimeUnit.SECONDS);
            System.out.println("打印结果信息：" + list.toString());
        } catch (InterruptedException e) {
            System.out.println("线程被中断");
            Thread.currentThread().interrupt();
            future.cancel(true);
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            System.out.println(e.getMessage());
            Throwable cause = e.getCause();
            System.out.println(cause);
        }
    }

    private void renderText() {
        System.out.println("开始渲染文本文件");
        try {
            Thread.sleep(20);
            System.out.println("渲染结束");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        FutureRender futureRender = new FutureRender();
        futureRender.render();
    }
}
