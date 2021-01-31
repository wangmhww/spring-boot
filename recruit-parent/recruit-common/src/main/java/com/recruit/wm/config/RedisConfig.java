package com.recruit.wm.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
//import org.redisson.Redisson;
//import org.redisson.config.Config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;


import java.time.Duration;

/**
 * @author wangm
 * @title: RedisConfig
 * @projectName recruit-parent
 * @description: TODO
 * @date 2020/12/1923:41
 */
@Configuration
@ConfigurationProperties("redis.config")
//@PropertySource("classpath:redis.properties")
@PropertySource(value = {"classpath:redis.properties"})
@Setter
@Getter
public class RedisConfig {
    public String hostName;

    public int port;

    public int maxTotal;

    public int maxIdle;

    public int minIdle;

    public int maxWaitMillis;

    @Bean
    public GenericObjectPoolConfig jedisPoolConfig(){
        GenericObjectPoolConfig jedisPoolConfig = new GenericObjectPoolConfig();
        jedisPoolConfig.setMaxTotal(maxTotal);  //最大连接数
        jedisPoolConfig.setMaxIdle(maxIdle); //最大空闲连接数
        jedisPoolConfig.setMinIdle(minIdle);   //最小空闲连接数
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis); //获取连接时最大等待时间
        jedisPoolConfig.setTestOnBorrow(true); //获取连接时检查是否可用
        jedisPoolConfig.setTestOnReturn(true); //返回连接时检查是否可用
        jedisPoolConfig.setTestWhileIdle(true);  //是否开启空闲资源监测
        jedisPoolConfig.setTimeBetweenEvictionRunsMillis(300000); //-1不检测   单位为毫秒  空闲资源监测周期
        jedisPoolConfig.setMinEvictableIdleTimeMillis(30*60*1000);//资源池中资源最小空闲时间 单位为毫秒  达到此值后空闲资源将被移除
        jedisPoolConfig.setNumTestsPerEvictionRun(300); //做空闲监测时，每次采集的样本数  -1代表对所有连接做监测
        return jedisPoolConfig;
    }

    @Bean
    public JedisConnectionFactory jedisConnectionFactory(GenericObjectPoolConfig jedisPoolConfig){
        JedisClientConfiguration jedisClientConfiguration = JedisClientConfiguration.builder().usePooling().poolConfig(jedisPoolConfig).and().readTimeout(Duration.ofMillis(2000)).build();
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setDatabase(0);
        redisStandaloneConfiguration.setPort(port);
        redisStandaloneConfiguration.setHostName(hostName);
        return new JedisConnectionFactory(redisStandaloneConfiguration, jedisClientConfiguration);
    }

    @Bean
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory jedisConnectionFactory) {

        RedisTemplate<String, Object> template = new RedisTemplate<>();
        // 配置连接工厂
        template.setConnectionFactory(jedisConnectionFactory);

        //使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值（默认使用JDK的序列化方式）
        Jackson2JsonRedisSerializer jacksonSeial = new Jackson2JsonRedisSerializer(Object.class);

        ObjectMapper om = new ObjectMapper();
        // 指定要序列化的域，field,get和set,以及修饰符范围，ANY是都有包括private和public
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        // 指定序列化输入的类型，类必须是非final修饰的，final修饰的类，比如String,Integer等会跑出异常
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        om.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        jacksonSeial.setObjectMapper(om);
        // 值采用json序列化
        template.setValueSerializer(jacksonSeial);
        //使用StringRedisSerializer来序列化和反序列化redis的key值
        template.setKeySerializer(new StringRedisSerializer());

        // 设置hash key 和value序列化模式
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(jacksonSeial);
        template.afterPropertiesSet();
        return template;
    }
    /**
     * 对hash类型的数据操作
     *
     * @param redisTemplate
     * @return
     */
    @Bean
    public HashOperations<String, String, Object> hashOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForHash();
    }

    /**
     * 对redis字符串类型数据操作
     *
     * @param redisTemplate
     * @return
     */
    @Bean
    public ValueOperations<String, Object> valueOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForValue();
    }

    /**
     * 对链表类型的数据操作
     *
     * @param redisTemplate
     * @return
     */
    @Bean
    public ListOperations<String, Object> listOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForList();
    }

    /**
     * 对无序集合类型的数据操作
     *
     * @param redisTemplate
     * @return
     */
    @Bean
    public SetOperations<String, Object> setOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForSet();
    }

    /**
     * 对有序集合类型的数据操作
     *
     * @param redisTemplate
     * @return
     */
    @Bean
    public ZSetOperations<String, Object> zSetOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForZSet();
    }
}
