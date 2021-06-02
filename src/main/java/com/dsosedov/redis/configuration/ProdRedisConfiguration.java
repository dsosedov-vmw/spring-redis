package com.dsosedov.redis.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

import java.util.Collection;
import java.util.logging.Logger;

@Profile("prod")
public class ProdRedisConfiguration extends RedisConfigurationBase {

    @Value("${spring.redis.cluster.nodes}")
    private Collection<String> redisClusterNodes;

    Logger logger = Logger.getLogger(ProdRedisConfiguration.class.getName());

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        logger.info("Connecting to Redis cluster...");
        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration(redisClusterNodes);
        for (RedisNode node : redisClusterConfiguration.getClusterNodes()) {
            logger.info(node.getHost() + ":" + node.getPort());
        }
        return new JedisConnectionFactory(redisClusterConfiguration);
    }

}
