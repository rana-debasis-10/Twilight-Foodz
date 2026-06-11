package com.twilight.services;


import com.razorpay.RazorpayException;
import com.twilight.dataTransferObjects.Payment;
import com.twilight.exceptions.UnauthorizedException;

import java.util.Map;

public interface PaymentService {
    public Map<String, Object> createPayment(Double total, String currency, String receipt) throws RazorpayException;
    public void handlePayment(Payment payment) throws UnauthorizedException;

    public void handleWebhook(String payload, String signature);


}
