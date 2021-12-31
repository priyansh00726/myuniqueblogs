package com.myuniqueblogs.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Blog {
    private final UUID id;
    @NotBlank @NotNull
    private final Person author;
    @NotBlank @NotNull
    private String description;
    private final List<String> tags;
    @NotBlank @NotNull
    private BlogType type;
    private final Date dateOfCreation;
    @NotBlank @NotNull
    private String content;
    private int likes;

    public Blog(@JsonProperty("id") UUID id,
                @JsonProperty("author_id") Person author,
                @JsonProperty("description") String description,
                @JsonProperty("tags") List<String> tags,
                @JsonProperty("type") BlogType type,
                @JsonProperty("_time") Date dateOfCreation,
                @JsonProperty("content") String content) {
        this.id = id;
        this.author = author;
        this.description = description;
        this.tags = tags;
        this.type = type;
        this.dateOfCreation = dateOfCreation;
        this.content = content;
        this.likes = 0;
    }

    public Blog(UUID id, Person author, String description, List<String> tags, BlogType type, Date dateOfCreation, String content, int likes) {
        this.id = id;
        this.author = author;
        this.description = description;
        this.tags = tags;
        this.type = type;
        this.dateOfCreation = dateOfCreation;
        this.content = content;
        this.likes = likes;
    }

    public Blog(UUID id, Blog blog) {
        this(id, blog.getAuthor(),
                blog.getDescription(),
                blog.getTags(),
                blog.getType(),
                blog.getDateOfCreation(),
                blog.getContent());
    }

    public UUID getId() {
        return id;
    }

    public Person getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getTags() {
        return tags;
    }

    public BlogType getType() {
        return type;
    }

    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    public String getContent() {
        return content;
    }

    public int getLikes() {
        return likes;
    }
}

