package com.myuniqueblogs.demo.dao;

import com.myuniqueblogs.demo.model.Blog;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BlogDAO {
    int addNewBlog(UUID id, Blog blog, UUID author_id);

    List<Blog> getAllBlogs(UUID author_id);

    Optional<Blog> selectBlogById(UUID id, UUID author_id);

    int deleteBlogById(UUID id, UUID author_id);

    int updateBlogById(UUID id, Blog newBlog, UUID author_id);

    default int addNewBlog(Blog blog, UUID author_id) {
        return addNewBlog(UUID.randomUUID(), blog, author_id);
    }
}
