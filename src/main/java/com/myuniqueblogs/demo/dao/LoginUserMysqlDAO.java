package com.myuniqueblogs.demo.dao;

import com.myuniqueblogs.demo.exceptions.AuthorNotFoundException;
import com.myuniqueblogs.demo.model.Gender;
import com.myuniqueblogs.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository("mysqlLoginDao")
public class LoginUserMysqlDAO implements LoginDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public LoginUserMysqlDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public static String convertStringToBinary(String input) {

        StringBuilder result = new StringBuilder();
        char[] chars = input.toCharArray();
        for (char aChar : chars) {
            result.append(
                    String.format("%8s", Integer.toBinaryString(aChar))   // char -> int, auto-cast
                            .replaceAll(" ", "0")                         // zero pads
            );
        }
        return result.toString();

    }

    @Override
    public Optional<Person> login(String username, String password) throws AuthorNotFoundException {
        String person_query = "SELECT * FROM person WHERE username = ?";
        List<Person> author = jdbcTemplate.query(person_query,
                (resultSet, i) -> new Person(
                        UUID.fromString(resultSet.getString("id")),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        Gender.valueOf(resultSet.getString("sex").equals("M") ? "MALE" : "FEMALE"),
                        resultSet.getString("description")
                ), username);
        if (author.isEmpty()) throw new AuthorNotFoundException("No author with this ID is present in the database.");
        return Optional.ofNullable(author.get(0));
    }
}
