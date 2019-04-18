package com.spring.cloud.moudle.study.demo.design.singleton;

/**
 * 策略模式
 * 根据不同的条件获取不同的子类实现
 *
 * @author wangmj
 * @since 2018/11/25
 */
public class StrategyDemo {
    public interface Cache {
        Cache add(String key, Object value);
    }

    public class RedisCache implements Cache {
        private RedisCache redisCache;
        private String key;
        private Object value;

        public RedisCache put(String key, Object value) {
            this.key = key;
            this.value = value;
            return this;
        }

        @Override
        public Cache add(String key, Object value) {
            System.out.println("redis缓存");
            redisCache = new RedisCache();
            redisCache.put(key, value);
            return redisCache;

        }

        public class MemoryCache implements Cache {

            @Override
            public Cache add(String key, Object value) {
                System.out.println("内存缓存");
                return new MemoryCache();
            }
        }

        public Cache getCache(String key) {
            if (key.length() > 10) {
                return new RedisCache();
            }
            return new MemoryCache();
        }
    }
}
