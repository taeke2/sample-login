package com.example.samplelogin.controller;

import com.example.samplelogin.entity.DemoUser;
import com.example.samplelogin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<DemoUser> getAllUsers(){
        return userService.getAllUsers();
    }
}
