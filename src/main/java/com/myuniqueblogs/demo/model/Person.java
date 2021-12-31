package com.myuniqueblogs.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Person {
    private final UUID id;
    @NotBlank @NotNull
    private final String name;
    @NotBlank @NotNull
    private String email;
    @NotBlank @NotNull
    private String username;
    private UUID hash;
    @NotBlank @NotNull
    private String password;
    @NotBlank @NotNull
    private final Gender sex;
    private String description;
//    private final List<Person> followers;
//    private final List<Person> following;


    public Person(
                  @JsonProperty("id") UUID id,
                  @JsonProperty("name") String name,
                  @JsonProperty("email") String email,
                  @JsonProperty("username") String username,
                  @JsonProperty("password") String password,
                  @JsonProperty("sex") Gender sex,
                  @JsonProperty("description") String description) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = generatePassword(password);
        this.sex = sex;
        this.description = description;
//        this.followers = new ArrayList<>();
//        this.following = new ArrayList<>();
    }

    private String generatePassword(String password) {
        return password;
    }

//    public int addFollower(Person follower) {
//        try {
//            this.followers.add(follower);
//            return 1;
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            return 0;
//        }
//    }

//    public int addFollowing(Person toFollow) {
//        try {
//            this.following.add(toFollow);
//            return 1;
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            return 0;
//        }
//    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Gender getSex() {
        return sex;
    }

    public String getDescription() {
        return description;
    }

//    public List<Person> getFollowers() {
//        return followers;
//    }
//
//    public List<Person> getFollowing() {
//        return following;
//    }
//
//    public int getFollowersCount() {
//        return followers.size();
//    }
//
//    public int getFollowingCount() {
//        return following.size();
//    }
}

