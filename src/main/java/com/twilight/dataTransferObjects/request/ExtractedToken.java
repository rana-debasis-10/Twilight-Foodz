package com.twilight.dataTransferObjects.request;

import com.twilight.components.security.UserDetailsImpl;
import com.twilight.types.Role;

public record ExtractedToken(String id, Role role, String mobNo) {
    public ExtractedToken(UserDetailsImpl userDetails) {
        this(userDetails.getId(), userDetails.getRole(), userDetails.getMobNo());
    }
}
