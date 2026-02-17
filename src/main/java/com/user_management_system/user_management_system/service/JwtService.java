package com.user_management_system.user_management_system.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.SecureRandom;
import java.util.Date;

@Service
public class JwtService {

    private static final Logger log = LoggerFactory.getLogger(JwtService.class);

    private final SecretKey secretKey;
    private final long expirationMillis = 1000L * 60 * 60; // 1 hour

    public JwtService(@Value("${jwt.secret:}") String base64Secret) {
        // If the secret is missing or placeholder, generate a temporary key (useful for local dev)
        if (base64Secret == null || base64Secret.isBlank() || base64Secret.contains("BASE64_ENCODED")) {
            log.warn("'jwt.secret' is not set or uses a placeholder. Generating a temporary in-memory secret key — NOT for production.");
            byte[] keyBytes = new byte[32];
            new SecureRandom().nextBytes(keyBytes);
            this.secretKey = Keys.hmacShaKeyFor(keyBytes);
            return;
        }

        try {
            byte[] keyBytes = Decoders.BASE64.decode(base64Secret);
            if (keyBytes.length < 32) {
                throw new IllegalStateException("The provided jwt.secret decodes to " + keyBytes.length + " bytes; it must be at least 32 bytes (256 bits) for HS256.");
            }
            this.secretKey = Keys.hmacShaKeyFor(keyBytes);
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException("Invalid jwt.secret: must be base64-encoded. Error: " + e.getMessage(), e);
        }
    }

    public String generateToken(String email) {

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMillis))
                .signWith(secretKey)
                .compact();
    }

    public String extractEmail(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();

    }

    public boolean isTokenExpired(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration()
                .before(new Date());
    }
}
