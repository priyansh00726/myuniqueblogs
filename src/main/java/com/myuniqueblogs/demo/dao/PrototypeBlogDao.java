package com.myuniqueblogs.demo.dao;

import com.myuniqueblogs.demo.model.Blog;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.*;

@Repository("prototypeBlogDao")
public class PrototypeBlogDao implements BlogDAO {
    private static final List<Blog> blogs = new ArrayList<>();

    @Override
    public int addNewBlog(UUID id, Blog blog, UUID author_id) {
        try {
            blogs.add(new Blog(
                    id,
                    blog.getAuthor(),
                    blog.getDescription(),
                    blog.getTags(),
                    blog.getType(),
                    Date.from(Instant.now()),
                    blog.getContent()
            ));
            return 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    @Override
    public List<Blog> getAllBlogs(UUID author_id) {
        return blogs;
    }

    @Override
    public Optional<Blog> selectBlogById(UUID id, UUID author_id) {
        return blogs.stream()
                .filter(blog -> blog.getId().equals(id))
                .findFirst();
    }

    @Override
    public int deleteBlogById(UUID id, UUID author_id) {
        Optional<Blog> blog = selectBlogById(id, author_id);
        if (blog.isEmpty()) return 0;
        blogs.remove(blog.get());
        return 1;
    }

    @Override
    public int updateBlogById(UUID id, Blog newBlog, UUID author_id) {
        return selectBlogById(id, author_id)
                .map(b -> {
                    int _index_blog = blogs.indexOf(b);
                    if (_index_blog >= 0) {
                        blogs.set(_index_blog, new Blog(id, newBlog));
                        return 1;
                    }
                    return 0;
                }).orElse(0);
    }
}
