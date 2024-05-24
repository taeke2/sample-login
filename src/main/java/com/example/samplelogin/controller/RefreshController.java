package com.example.samplelogin.controller;

import com.example.samplelogin.dto.RefreshTokenRequest;
import com.example.samplelogin.dto.TokenResponse;
import com.example.samplelogin.security.JwtTokenProvider;
import com.example.samplelogin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RefreshController {

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private UserService userService;

    @PostMapping("/refresh")
    public TokenResponse refresh(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        String refreshToken = refreshTokenRequest.getRefreshToken();

        if (tokenProvider.validateToken(refreshToken)) {
            String userId = tokenProvider.getUserIdFromJWT(refreshToken);
            User user = (User) userService.loadUserByUsername(userId);
            Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String newAccessToken = tokenProvider.generateAccessToken(authentication);
            return new TokenResponse(newAccessToken, refreshToken);
        } else {
            return new TokenResponse("fail", "fail");
        }
    }
}