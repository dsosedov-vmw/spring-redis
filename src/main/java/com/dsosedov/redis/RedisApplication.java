package com.dsosedov.redis;

import io.nats.streaming.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeoutException;

@Slf4j
@SpringBootApplication
public class RedisApplication {

    public static void main(String[] args) throws TimeoutException, IOException, InterruptedException {
        ApplicationContext context = SpringApplication.run(RedisApplication.class, args);
        StreamingConnection sc = (StreamingConnection)context.getBean("getNatsConnection");

        final CountDownLatch latch = new CountDownLatch(1);

        Subscription sub = sc.subscribe("dataIn", new MessageHandler() {
            public void onMessage(Message m) {
                log.debug("Received a message: %s\n", new String(m.getData()));
                //latch.countDown();
            }
        }, new SubscriptionOptions.Builder().deliverAllAvailable().build());

        latch.await();

        sub.unsubscribe();

        sc.close();
    }

}
