package com.springbootecommerce.server.security;


import com.springbootecommerce.server.accounts.models.AppUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {

    private Key key;

    public JwtUtils(@Value("${jwt.key}") String signKey) {
        this.key = Keys.hmacShaKeyFor(signKey.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(AppUser appUser) {
        return Jwts.builder()
                .setSubject(appUser.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*60*10))
                .signWith(key)
                .compact();
    }

}
