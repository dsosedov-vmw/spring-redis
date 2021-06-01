package com.dsosedov.redis.controllers;

import com.dsosedov.redis.entities.Foo;
import com.dsosedov.redis.models.FooRequest;
import com.dsosedov.redis.repositories.FooRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/foo")
public class FooController {

    private FooRepository fooRepository;

    public FooController(FooRepository fooRepository) {
        this.fooRepository = fooRepository;
    }

    @PostMapping
    public ResponseEntity<Void> post(@RequestBody FooRequest request) {
        Foo foo = new Foo();
        foo.setBar(request.getBar());
        fooRepository.save(foo);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public String get() {
        if (fooRepository.findAll().iterator().hasNext()) {
            return fooRepository.findAll().iterator().next().getBar();
        }
        return "nothing found";
    }

}
