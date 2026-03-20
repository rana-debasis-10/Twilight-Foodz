package com.twilight.services;

import org.springframework.transaction.annotation.Transactional;

public interface MessageService {
    @Transactional
    boolean sendOtp(String mobNo);
}