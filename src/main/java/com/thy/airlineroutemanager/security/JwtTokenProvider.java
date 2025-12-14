package com.thy.airlineroutemanager.security;

import com.thy.airlineroutemanager.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    private final JwtTokenConfigProperties jwtTokenConfigProperties;

    // Token üretme
    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("role", user.getRole().name())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtTokenConfigProperties.getExpiration()))
                .signWith(Keys.hmacShaKeyFor(jwtTokenConfigProperties.getSecret().getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }

    // Token parse etme
    public Claims parseClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(jwtTokenConfigProperties.getSecret().getBytes()))
                .build()
                .parseClaimsJws(token)   // parseClaimsJws token'ı parse eder ve imzayı doğrular
                .getBody();              // Claims elde edilir
    }
}
