package com.twilight.dataTransferObjects;


public record Payment(String orderId, String paymentId, String signature){}

