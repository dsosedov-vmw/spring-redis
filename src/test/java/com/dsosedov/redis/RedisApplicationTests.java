package com.dsosedov.redis;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {"nats.clientId=spring-redis-test-0"})
class RedisApplicationTests {

    @Test
    void contextLoads() {
    }

}
