package com.dsosedov.redis.controllers;

import com.dsosedov.redis.entities.Foo;
import com.dsosedov.redis.repositories.FooRepository;
import io.nats.streaming.StreamingConnection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Iterator;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class FooControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FooRepository fooRepository;

    @MockBean
    private StreamingConnection streamingConnection;

    private Iterator<Foo> iterator;

    @BeforeEach
    void setUp() {
        when(fooRepository.findAll()).thenReturn(() -> iterator);
    }

    @Test
    void postShouldReturnCreated() throws Exception {
        mockMvc.perform(
                post("/api/v1/foo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"bar\":\"baz\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().string(""));
    }

    @Test
    void getShouldReturnReady() throws Exception {
        iterator = new Iterator<>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public Foo next() {
                return null;
            }
        };
        mockMvc.perform(
                get("/api/v1/foo"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"status\":\"READY\"}"));
    }

    @Test
    void getShouldReturnSomething() throws Exception {
        iterator = new Iterator<>() {
            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public Foo next() {
                Foo foo = new Foo();
                foo.setBar("baz");
                return foo;
            }
        };
        mockMvc.perform(
                get("/api/v1/foo"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"status\":\"IN_PROGRESS\",\"bar\":\"baz\"}"));
    }

}
