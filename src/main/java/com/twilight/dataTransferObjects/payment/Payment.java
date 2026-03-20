package com.twilight.dataTransferObjects.payment;


public record Payment(String orderId, String paymentId, String signature){}

