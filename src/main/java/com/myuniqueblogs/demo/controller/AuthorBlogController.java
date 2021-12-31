package com.myuniqueblogs.demo.controller;

import com.myuniqueblogs.demo.model.Blog;
import com.myuniqueblogs.demo.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequestMapping("/api/v1/blogs")
@RestController
public class AuthorBlogController {
    private final BlogService blogService;

    @Autowired
    public AuthorBlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @RequestMapping(value = "/{author_id}", method = RequestMethod.POST)
    public void addNewBlog(@Valid @NonNull @RequestBody Blog blog,
                           @PathVariable("author_id") UUID author_id) {
        blogService.addNewBlog(blog, author_id);
    }

    @RequestMapping(value = "/{author_id}/all", method = RequestMethod.GET)
    public List<Blog> getAllBlogs(@PathVariable("author_id") UUID author_id) {
        return blogService.getAllBlogs(author_id);
    }

    @RequestMapping(value = "/{author_id}/{id}", method = RequestMethod.DELETE)
    public void deleteBlog(@PathVariable("id") UUID id,
                           @PathVariable("author_id") UUID author_id) {
        blogService.deleteBlog(id, author_id);
    }

    @RequestMapping(value = "/{author_id}/{id}", method = RequestMethod.PUT)
    public void updateBlog(@PathVariable("id") UUID id,
                           @RequestBody Blog blog,
                           @PathVariable("author_id") UUID author_id) {
        blogService.updateBlog(id, blog, author_id);
    }
}
