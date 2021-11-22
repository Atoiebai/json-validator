package com.example.jsonvalidator.controller;


import com.example.jsonvalidator.annotation.ValidJson;
import com.example.jsonvalidator.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.*;

import static com.example.jsonvalidator.config.SchemaLocations.USERS;

@RestController
@RequestMapping("api/v1/users")

public class UserController {
    private final Map<Integer, User> users = new HashMap<>();
    private static final Logger log = LoggerFactory.getLogger(UserController.class.toString());
    private Integer id = 0;



    @GetMapping
    public Collection<User> getUsers() {
        return users.values();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Integer id) {
        return users.get(id);
    }

    @PostMapping
    public User add(@ValidJson(USERS) User user) {
        log.info("Got add() invoked with {} ", user);

        return users.put(this.id++, user);
    }

    @GetMapping("/echo")
    public Mono<User> echo() {
        Mono<User> userMono = WebClient.builder().baseUrl("http://localhost:8081/api/v1/users")
                .build()
                .post()
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue("{\"name\" : \"Islam\" , \n\"age\" : 18\n}").retrieve().bodyToMono(User.class);

        return userMono;
    }


}
