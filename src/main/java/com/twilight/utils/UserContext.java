package com.twilight.utils;

import com.twilight.annotations.MobileNumber;
import lombok.Getter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.Objects;

@Component
@RequestScope
@Getter
public class UserContext {
    @MobileNumber
    private final String mobNo;
    private final Object credential;
    UserContext(){
        this.mobNo = (String) Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication()).getPrincipal();
        this.credential = SecurityContextHolder.getContext().getAuthentication().getCredentials();
    }
}
