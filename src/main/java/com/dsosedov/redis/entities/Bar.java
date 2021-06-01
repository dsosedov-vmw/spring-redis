package com.dsosedov.redis.entities;

import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

@Data
@RedisHash("bar")
public class Bar {

}
