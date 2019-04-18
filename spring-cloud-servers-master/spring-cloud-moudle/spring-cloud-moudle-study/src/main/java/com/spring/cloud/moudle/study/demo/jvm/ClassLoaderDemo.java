package com.spring.cloud.moudle.study.demo.jvm;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author wangmj
 * @since 2019/2/17
 */
public class ClassLoaderDemo {

    private static int a;//准备阶段赋值初始化为0；

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        ClassLoader classLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    InputStream inputStream = getClass().getResourceAsStream(fileName);
                    if (inputStream == null) {
                        return super.loadClass(name);
                    }
                    byte[] bytes = new byte[inputStream.available()];
                    inputStream.read(bytes);
                    return defineClass(name, bytes, 0, bytes.length);
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new ClassNotFoundException(name);
                }
            }
        };
        Object instance = classLoader.loadClass("com.spring.cloud.moudle.study.demo.jvm.ClassLoaderDemo").newInstance();
        System.out.println(instance.getClass());
        System.out.println(instance instanceof com.spring.cloud.moudle.study.demo.jvm.ClassLoaderDemo);
        System.out.println(a);
    }
}
