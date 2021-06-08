package com.dsosedov.redis.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

import java.util.Collection;

@Profile("prod")
@Slf4j
public class ProdRedisConfiguration extends RedisConfigurationBase {

    @Value("${spring.redis.cluster.nodes}")
    private Collection<String> redisClusterNodes;

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        log.debug("Connecting to Redis cluster...");
        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration(redisClusterNodes);
        for (RedisNode node : redisClusterConfiguration.getClusterNodes()) {
            log.debug(node.getHost() + ":" + node.getPort());
        }
        return new JedisConnectionFactory(redisClusterConfiguration);
    }

}
