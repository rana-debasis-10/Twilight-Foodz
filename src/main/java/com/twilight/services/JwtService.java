package com.twilight.services;

import com.twilight.types.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.SecretKey;
import java.util.*;

public interface JwtService {

    public String generateToken( String mobNo,Role role) ;

    public String generateToken(String mobNo , Role role , String establishment);

    public boolean isTokenValid(String token) ;

    public Claims extractClaims(String token) ;


}
