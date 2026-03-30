package com.twilight.services;

import org.springframework.transaction.annotation.Transactional;

public interface MessageService {
    @Transactional
    void sendOtp(String mobNo);
    @Transactional
    String checkOtp(String mobNo);
}