package com.georgehabib.Tags_comments.auth;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.georgehabib.Tags_comments.Users.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expirationTimeMs;

    private Key secretKey;


    @PostConstruct
    public void init() {
        byte[] keyBytes = Base64.getDecoder().decode(secret);
        secretKey = Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(User user){
        
        Map<String, Object> claims = new HashMap<>();
    
        return createToken(claims, user.getUsername());   
    }


    private String createToken(Map<String, Object> claims, String subject){
        return Jwts.builder()
            .setClaims(claims)     
            .setSubject(subject)    
            .setIssuedAt(new Date(System.currentTimeMillis()))  
            .setExpiration(new Date(System.currentTimeMillis() + expirationTimeMs))     
            .signWith(secretKey)    
            .compact();             
    }


    public String extractUsername(String token){
        return Jwts.parserBuilder()
            .setSigningKey(secretKey)   
            .build()
            .parseClaimsJws(token)      
            .getBody()                  
            .getSubject();              
    }


    public Date extractExpiration(String token){
        return Jwts.parserBuilder()
            .setSigningKey(secretKey)  
            .build() 
            .parseClaimsJws(token)      
            .getBody()                  
            .getExpiration();              
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {

        String username = extractUsername(token);

        boolean isUsernameValid = username.equals(userDetails.getUsername());

        Date expiration = extractExpiration(token);
        boolean isTokenExpired = expiration.before(new Date());
        boolean isTokenValid = !isTokenExpired;

        return isUsernameValid && isTokenValid;
    }


}
            

