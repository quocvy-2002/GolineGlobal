package com.example.demo.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import java.nio.charset.StandardCharsets;
import javax.crypto.SecretKey;

public class JwtUtil {
    private static final String SECRET_KEY = "01234567890123456789012345678901";
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 10; // 10 giờ

    private static final SecretKey KEY = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

    public static String generateToken(String email, Integer lessorId) {
        return Jwts.builder()
                .setSubject(email)
                .claim("lessorId", lessorId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // 10 giờ
                .signWith(KEY, SignatureAlgorithm.HS256) // Sửa lại để dùng KEY
                .compact();
    }

    public static String getEmailFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(KEY) // Sử dụng đúng KEY
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
