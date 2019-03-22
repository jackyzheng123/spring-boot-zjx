package com.zjx.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @Description
 * @Author Carson Cheng
 * @Date 2019/3/20 13:34
 * @Version V1.0
 **/
@Configuration
public class RedisConfig extends CachingConfigurerSupport {


    private final RedisTemplate redisTemplate;

    @Autowired
    public RedisConfig(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisSerializer<String> stringSerializer = new StringRedisSerializer();
        RedisSerializer<Object> jsonString = new GenericToStringSerializer<>(Object.class);
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(jsonString);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(jsonString);
        return redisTemplate;
    }

    /**
     * RedissonClient
     */
    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://localhost:6379");
        return Redisson.create(config);
    }

}