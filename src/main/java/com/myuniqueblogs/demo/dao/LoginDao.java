package com.myuniqueblogs.demo.dao;

import com.myuniqueblogs.demo.model.Person;

import java.util.Optional;

public interface LoginDao {
    Optional<Person> login(String username, String password);
}
