package com.example.samplelogin.controller;

import com.example.samplelogin.dto.LoginRequest;
import com.example.samplelogin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        boolean isValidUser = userService.validateUser(loginRequest.getId(), loginRequest.getPw());
        return isValidUser ? "ok" : "fail";
    }
}
