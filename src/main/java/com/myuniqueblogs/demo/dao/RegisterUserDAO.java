package com.myuniqueblogs.demo.dao;

import com.myuniqueblogs.demo.model.Person;

import java.util.UUID;

public interface RegisterUserDAO {
    boolean register(UUID author_id, Person user);

    default boolean register(Person user) {
        return register(UUID.randomUUID(), user);
    }
}
