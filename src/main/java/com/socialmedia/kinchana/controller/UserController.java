package com.socialmedia.kinchana.controller;

import com.socialmedia.kinchana.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("")
    public ResponseEntity<?> getUserById(@RequestParam int id) {
        return new ResponseEntity<>(userRepository.findById(id), HttpStatus.OK);
    }

}
