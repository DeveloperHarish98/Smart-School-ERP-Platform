package com.springboot.schoolmanagement.security;

import com.springboot.schoolmanagement.Enum.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtils {

  @Value("${jwt.secret}")
  private String secret;

  @Value("${jwt.expiration-ms}")
  private long expirationMs;

  private SecretKey getSigningKey() {
    return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
  }

  public String generateToken(String email, Role role) {
    return Jwts.builder()
        .subject(email)                                         
        .claim("role", role.name())                             
        .issuedAt(new Date())                                  
        .expiration(new Date(System.currentTimeMillis() + expirationMs)) 
        .signWith(getSigningKey())                             
        .compact();                                             
  }

  public String extractEmail(String token) {
    return parseClaims(token).getSubject();
  }

  public boolean validateToken(String token) {
    try {
      parseClaims(token); 
      return true;
    } catch (Exception e) {
      return false;
    }
  }
  
  private Claims parseClaims(String token) {
    return Jwts.parser()
        .verifyWith(getSigningKey()) 
        .build()
        .parseSignedClaims(token)   
        .getPayload();              
  }
}
