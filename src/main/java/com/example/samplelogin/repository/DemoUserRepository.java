package com.example.samplelogin.repository;

import com.example.samplelogin.entity.DemoUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DemoUserRepository extends JpaRepository<DemoUser, Long> {
    Optional<DemoUser> findByUserId(String username);
}
