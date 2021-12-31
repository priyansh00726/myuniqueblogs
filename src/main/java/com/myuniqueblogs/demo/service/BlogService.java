package com.myuniqueblogs.demo.service;

import com.myuniqueblogs.demo.dao.BlogDAO;
import com.myuniqueblogs.demo.model.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BlogService {
    private final BlogDAO blogDao;

    @Autowired
    public BlogService(@Qualifier("mysqlDao") BlogDAO blogDao) {
        this.blogDao = blogDao;
    }

    public int addNewBlog(Blog blog, UUID author_id) {
        return blogDao.addNewBlog(blog, author_id);
    }

    public List<Blog> getAllBlogs(UUID author_id) {
        return blogDao.getAllBlogs(author_id);
    }

    public int deleteBlog(UUID id, UUID author_id) {
        return blogDao.deleteBlogById(id, author_id);
    }

    public int updateBlog(UUID id, Blog newBlog, UUID author_id) {
        return blogDao.updateBlogById(id, newBlog, author_id);
    }
}