package com.myuniqueblogs.demo.dao;

import com.myuniqueblogs.demo.exceptions.AuthorNotFoundException;
import com.myuniqueblogs.demo.model.Blog;
import com.myuniqueblogs.demo.model.BlogType;
import com.myuniqueblogs.demo.model.Gender;
import com.myuniqueblogs.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.QueryCreationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.*;

@Repository("mysqlDao")
public class MysqlBlogDao implements BlogDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MysqlBlogDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private Person getPersonFromID(UUID author_id) throws AuthorNotFoundException {
        String person_query = "SELECT * FROM person WHERE author_id = ?";
        List<Person> author = jdbcTemplate.query(person_query,
                (resultSet, i) -> new Person(
                        UUID.fromString(resultSet.getString("id")),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        Gender.valueOf(resultSet.getString("sex").equals("M") ? "MALE" : "FEMALE"),
                        resultSet.getString("description")
                ), author_id.toString());
        if (author.isEmpty()) throw new AuthorNotFoundException("No author with this ID is present in the database.");
        return author.get(0);
    }

    @Override
    public int addNewBlog(UUID id, Blog blog, UUID author_id) {
        String query = "INSERT INTO blog(blog.id, blog.author_id, blog.description, blog.tags, blog.type, blog._time, blog.content, blog.likes) " +
                "VALUES (?,?,?,?,?,NOW(),?,0)";
        StringBuilder sb = new StringBuilder();
        if (blog.getTags() != null) blog.getTags().forEach(sb::append);
        try {
            jdbcTemplate.update(
                    query,
                    id.toString(),
                    author_id.toString(),
                    blog.getDescription(),
                    sb.toString(),
                    blog.getType().toString(),
                    blog.getContent());
            return 0;
        } catch (QueryCreationException e) {
            System.out.println(e.getMessage());
        }
        return 1;
    }

    @Override
    public List<Blog> getAllBlogs(UUID author_id) {
        String query = "SELECT * FROM blog WHERE blog.author_id = ?";
        Person author;
        try {
            author = getPersonFromID(author_id);
        } catch (AuthorNotFoundException e) {
            throw new AuthorNotFoundException(e.getMessage());
        }
        return jdbcTemplate.query(query,
                (resultSet, i) -> new Blog(
                        UUID.fromString(resultSet.getString("id")),
                        author,
                        resultSet.getString("description"),
                        List.of(resultSet.getString("tags").split(":")),
                        BlogType.valueOf(resultSet.getString("type")),
                        resultSet.getObject("_time", Date.class),
                        resultSet.getString("content")
                ), author_id.toString());
    }

    @Override
    public Optional<Blog> selectBlogById(UUID id, UUID author_id) {

        return Optional.empty();
    }

    @Override
    public int deleteBlogById(UUID id, UUID author_id) {
        return 0;
    }

    @Override
    public int updateBlogById(UUID id, Blog newBlog, UUID author_id) {
        return 0;
    }
}
