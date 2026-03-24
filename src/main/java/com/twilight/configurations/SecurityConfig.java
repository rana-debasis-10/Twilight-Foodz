package com.twilight.configurations;

import com.twilight.filters.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**", "/find/**", "/error","/order.html")
                            .permitAll()
                        .requestMatchers("/api/admin/**")
                            .hasRole("ADMIN")
                        .requestMatchers("/api/customer/**")
                            .hasAnyRole("ADMIN", "CUSTOMER","MERCHANT","DRIVER")

                        .requestMatchers("/api/restaurant/**")
                        .hasAnyRole("ADMIN", "MERCHANT")

                        .requestMatchers("/api/driver/**")
                        .hasAnyRole("ADMIN", "DRIVER")

                        .anyRequest().authenticated()
                )

                .httpBasic(AbstractHttpConfigurer::disable)
                
                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }

}
