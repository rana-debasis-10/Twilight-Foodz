package com.twilight.services;

import com.twilight.annotations.MobileNumber;
import org.springframework.transaction.annotation.Transactional;
import software.amazon.awssdk.annotations.NotNull;

public interface MessageService {

    @Transactional
    void sendOtp(@MobileNumber @NotNull  String mobNo);

    @Transactional
    boolean verifyOtp(@MobileNumber @NotNull String mobNo, Integer otp);
}