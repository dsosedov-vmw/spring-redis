package com.dsosedov.redis;

import io.nats.streaming.StreamingConnection;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class RedisApplicationTests {

    @MockBean
    private StreamingConnection streamingConnection;

    @Test
    void contextLoads() {
    }

}
