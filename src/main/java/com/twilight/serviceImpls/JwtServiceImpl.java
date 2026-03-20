package com.twilight.serviceImpls;

import com.twilight.services.JwtService;
import com.twilight.types.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtServiceImpl implements JwtService {
    @Value("${jwt.secret.key}")
    private String secretKey ;

    private SecretKey getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    @Override
    public String generateToken(String id, Role role, String mobNo) {
        Map<String, Object> claims = new HashMap<>();

        return Jwts.builder()
                .subject(id)
                .claim("Role",role.toString())
                .claim("Mobile",mobNo)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() +1000L * 60 * 60 * 30))
                .signWith(getKey())
                .compact();

    }
    @Override
    public boolean isTokenValid(String token){
            try {
                extractClaims(token);
                return true;
            } catch (Exception e) {
                return false;
            }
    }
    @Override
    public Claims extractClaims(String token){
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

}
