package com.example.samplelogin.controller;

import com.example.samplelogin.dto.RegisterRequest;
import com.example.samplelogin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest registerRequest) {
        userService.registerUser(registerRequest.getId(), registerRequest.getPw());
        return "User registered successfully";
    }
}
