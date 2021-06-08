package com.dsosedov.redis.configuration;

import io.nats.streaming.Options;
import io.nats.streaming.StreamingConnection;
import io.nats.streaming.StreamingConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class NatsConfiguration {

    @Value("${nats.url}")
    private String natsUrl;

    @Value("${nats.clusterId}")
    private String natsCluster;

    @Value("${nats.clientId}")
    private String natsClient;

    @Bean
    public StreamingConnection getNatsConnection() throws IOException, InterruptedException {
        Options options = new Options.Builder()
                .natsUrl(natsUrl)
                .clusterId(natsCluster)
                .clientId(natsClient)
                .build();
        StreamingConnectionFactory cf = new StreamingConnectionFactory(options);
        return cf.createConnection();
    }

}
