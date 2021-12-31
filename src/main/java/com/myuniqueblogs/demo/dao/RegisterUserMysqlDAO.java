package com.myuniqueblogs.demo.dao;

import com.myuniqueblogs.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.QueryCreationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("mysqlRegisterDao")
public class RegisterUserMysqlDAO implements RegisterUserDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public RegisterUserMysqlDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean register(UUID author_id, Person user) {
        String query = "INSERT INTO person VALUES(?,?,?,?,?,?,?)";
        try {
            jdbcTemplate.update(query,
                    author_id.toString(),
                    user.getName(),
                    user.getEmail(),
                    user.getUsername(),
                    user.getPassword(),
                    user.getSex().toString().equals("MALE") ? "M" : "F",
                    user.getDescription());
            return true;
        } catch (QueryCreationException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
