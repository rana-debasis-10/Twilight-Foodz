package com.twilight.filters;
import com.twilight.types.Role;
import com.twilight.objects.security.UserDetailsImpl;
import com.twilight.services.JwtService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

        JwtService jwtService;

        @Autowired
        public JwtAuthenticationFilter(JwtService jwtService) {
            this.jwtService = jwtService;
        }

        @Override
        protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {

            String authHeader = request.getHeader("Authorization");

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                filterChain.doFilter(request, response);
                return;
            }

            String token = authHeader.substring(7);
            if (!jwtService.isTokenValid(token)) {
                filterChain.doFilter(request, response);
                return;
            }

            Claims claims = jwtService.extractClaims(token);
            String id = claims.getSubject();

            Role role = Role.valueOf(claims.get("Role", String.class));

            String mobNo = claims.get("Mobile",String.class);


            Collection<GrantedAuthority> authorities = new ArrayList<>();

            authorities.add(
                    new SimpleGrantedAuthority("ROLE_" + role.toString())
            );
            UserDetailsImpl userDetailsImpl= new UserDetailsImpl(id, role, mobNo, true, true, true, true);

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(userDetailsImpl, null, authorities);

            authentication.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request)
            );

            SecurityContextHolder.getContext()
                    .setAuthentication(authentication);

            filterChain.doFilter(request, response);
        }
}
