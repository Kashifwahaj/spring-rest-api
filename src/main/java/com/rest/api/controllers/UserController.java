package com.rest.api.controllers;

import com.rest.api.models.User;
import com.rest.api.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping
    public List<User> findAllUsers() {
        return  (List<User>) userRepo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findUserById(@PathVariable(value = "id") long id) {
        Optional<User> user = userRepo.findById(id);

        if(user.isPresent()) {
            return ResponseEntity.ok().body(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public User saveUser(@Validated @RequestBody User user) {
        return userRepo.save(user);
    }
}
