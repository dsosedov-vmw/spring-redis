package com.dsosedov.redis.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Data
@RedisHash("Foo")
public class Foo {
    @Id
    private Integer id;
    private String bar;
}
