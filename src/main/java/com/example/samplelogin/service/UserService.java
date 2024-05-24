package com.example.samplelogin.service;

import com.example.samplelogin.entity.DemoUser;
import com.example.samplelogin.repository.DemoUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private final DemoUserRepository demoUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(DemoUserRepository demoUserRepository, PasswordEncoder passwordEncoder) {
        this.demoUserRepository = demoUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        DemoUser user = demoUserRepository.findByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + userId));

        return User.builder()
                .username(user.getUserId())
                .password(user.getPassword())
                .authorities("USER") // 기본 권한을 "USER"로 설정
                .build();
    }

    public void registerUser(String userId, String password) {
        DemoUser user = new DemoUser();
        user.setUserId(userId);
        user.setPassword(passwordEncoder.encode(password));
        demoUserRepository.save(user);
    }

    public List<DemoUser> getAllUsers() {
        return demoUserRepository.findAll();
    }
}