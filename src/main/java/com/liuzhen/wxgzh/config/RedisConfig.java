package com.liuzhen.wxgzh.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

//@Configuration
//@EnableAutoConfiguration
public class RedisConfig {


//    @Bean
//    @ConfigurationProperties(prefix = "spring.redis.pool")
//    public JedisPoolConfig getRedisConfig(){
//        JedisPoolConfig config = new JedisPoolConfig();
//        return config;
//    }
//
//
//    @Bean
//    @ConfigurationProperties(prefix = "spring.redis")
    public JedisConnectionFactory getConnectionFactory() {
        JedisConnectionFactory factory = new JedisConnectionFactory();
//        factory.setUsePool(true);
//
//        JedisPoolConfig config = getRedisConfig();
//        factory.setPoolConfig(config);
        return factory;
    }
//
//    @Bean
//    public RedisTemplate<?, ?> getRedisTemplate() {
//        JedisConnectionFactory factory = getConnectionFactory();
//        RedisTemplate<?, ?> template = new StringRedisTemplate(factory);
//        return template;
//    }


    /**
     * 存入对象tostring后的信息
     * @return
     */
//    @Bean
    public RedisTemplate<String,String> getRedisTemplate(){
        RedisTemplate<String,String> redisTemplate = new StringRedisTemplate();
        redisTemplate.setConnectionFactory(getConnectionFactory());
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}
