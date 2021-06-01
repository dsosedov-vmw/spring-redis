package com.dsosedov.redis.repositories;

import com.dsosedov.redis.entities.Foo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FooRepository extends CrudRepository<Foo, Integer> {
}
