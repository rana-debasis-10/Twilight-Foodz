package com.twilight.dataTransferObjects;

import com.twilight.annotations.MobileNumber;
import com.twilight.objects.User;

public record UserR(String name, String email, @MobileNumber String mobNo, String password ) {
    public UserR(User user){
        this(user.getName(), user.getEmail(), user.getMobNo(), user.getPassword());
    }
}
