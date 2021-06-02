package com.dsosedov.redis.models;

import lombok.Data;

@Data
public class FooResponse {
    private FooStatus status;
    private String bar;
}
