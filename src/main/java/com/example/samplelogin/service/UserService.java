package com.example.samplelogin.service;

import com.example.samplelogin.entity.DemoUser;
import com.example.samplelogin.repository.DemoUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private DemoUserRepository demoUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean validateUser(String username, String password) {
        Optional<DemoUser> user = demoUserRepository.findByUserId(username);
        return user.isPresent() && passwordEncoder.matches(password, user.get().getPassword());
    }

    public void registerUser(String userId, String password) {
        DemoUser user = new DemoUser();
        user.setUserId(userId);
        user.setPassword(passwordEncoder.encode(password));
        demoUserRepository.save(user);
    }
}
