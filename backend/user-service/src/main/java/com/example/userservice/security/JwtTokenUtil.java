package com.example.userservice.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenUtil {

    private final String secret = "secret"; // Change this to a secure key
    private final long expirationTime = 1000 * 60 * 60; // 1 hour

    /*
     * @Value("${jwt.secret}")
     * private String secret;
     * 
     * @Value("${jwt.expiration-time}")
     * private long expirationTime;
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public boolean validateToken(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getExpiration();
    }

    public String extractUsername(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    /*
     * private String createToken(Map<String, Object> claims, String subject) {
     * return Jwts.builder()
     * .setClaims(claims)
     * .setSubject(subject)
     * .setIssuedAt(new Date(System.currentTimeMillis()))
     * .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
     * .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
     * .compact();
     * }
     */
}
