package com.twilight.services;

import com.twilight.annotations.MobileNumber;
import org.springframework.transaction.annotation.Transactional;

public interface MessageService {
    @Transactional
    void sendOtp(String mobNo);
    @Transactional
    boolean verifyOtp(String mobNo,int otp);
}