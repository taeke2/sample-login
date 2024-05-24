package com.example.samplelogin.util;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.util.Base64;

// Base64로 인코딩된 512비트 길이의 비밀키 자동 생성
public class SecretKeyGenerator {
    public static void main(String[] args) {
        byte[] key = Keys.secretKeyFor(SignatureAlgorithm.HS512).getEncoded();
        String encodedKey = Base64.getEncoder().encodeToString(key);
        System.out.println("encodedKey = " + encodedKey);
    }
}
