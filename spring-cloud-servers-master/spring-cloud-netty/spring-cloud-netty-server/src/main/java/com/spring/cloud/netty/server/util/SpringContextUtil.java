package com.spring.cloud.netty.server.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author wangmj
 * @since 2018/11/13
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {

    private volatile static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (SpringContextUtil.applicationContext == null) {
            synchronized (this) {
                SpringContextUtil.applicationContext = applicationContext;
            }
        }
    }

    //获取applicationContext
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    //通过name获取 Bean.
    public static Object getBean(String name) {
        try {
            return getApplicationContext().getBean(name);
        } catch (Exception e) {
            return null;
        }
    }

}
