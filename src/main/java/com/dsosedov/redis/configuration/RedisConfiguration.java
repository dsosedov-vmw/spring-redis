package com.dsosedov.redis.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

import java.util.Collection;
import java.util.logging.Logger;

@Configuration
@EnableRedisRepositories
public class RedisConfiguration {

    @Value("${spring.redis.cluster.nodes}")
    private Collection<String> redisClusterNodes;

    Logger logger = Logger.getLogger(RedisConfiguration.class.getName());

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        logger.info("Connecting to Redis cluster...");
        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration(redisClusterNodes);
        for (RedisNode node : redisClusterConfiguration.getClusterNodes()) {
            logger.info(node.getHost() + ":" + node.getPort());
        }
        return new JedisConnectionFactory(redisClusterConfiguration);
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        return template;
    }

}
