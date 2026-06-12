package com.twilight.services;

import com.twilight.annotations.MobileNumber;
import com.twilight.types.Role;
import io.jsonwebtoken.Claims;
import jakarta.validation.constraints.NotNull;
import org.jspecify.annotations.NonNull;

public interface JwtService {

    public String generateToken(@MobileNumber @NotNull String mobNo, @NonNull Role role) ;

    public String generateToken(@MobileNumber @NotNull String mobNo , @NonNull Role role , Object credential);

    public String generateToken(@MobileNumber @NotNull String mobNo , @NonNull Role role , Object credential, Long lifespan);

    public boolean isTokenValid(@NonNull @NotNull String token) ;

    public Claims extractClaims(@NonNull @NotNull String token) ;


}
