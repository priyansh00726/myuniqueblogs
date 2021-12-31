package com.myuniqueblogs.demo.controller;

import com.myuniqueblogs.demo.model.Person;
import com.myuniqueblogs.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@RequestMapping("/api/v1/blogs")
@RestController
public class AuthorController {
    private final PersonService personService;

    @Autowired
    public AuthorController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/register")
    public String addNewUser(@Valid @NotNull @RequestBody Person user) {
        return personService.addNewUser(user);
    }

//    @GetMapping("/login/{username}-{password}")
//    public Person loginUser(@Valid @NotNull @RequestBody String data) {
//        String[] raw_data = data.split(":");
//        return personService.loginUser(raw_data[0], raw_data[1]);
//    }

    @GetMapping("/login/{username}/{password}")
    public Person loginUser(@PathVariable("username") String username, @PathVariable("password") String password) {
        return personService.loginUser(username, password);
    }
}
