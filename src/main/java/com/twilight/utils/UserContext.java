package com.twilight.utils;

import lombok.Getter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.Objects;

@Component
@RequestScope
@Getter
public class UserContext {
    private final String Mobile_Number;
    private final String establishment;
    UserContext(){
        this.Mobile_Number = (String) Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication()).getPrincipal();
        this.establishment = (String)SecurityContextHolder.getContext().getAuthentication().getCredentials();
    }
}
