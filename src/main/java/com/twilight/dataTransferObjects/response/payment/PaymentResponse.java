package com.twilight.dataTransferObjects.response.payment;


public record PaymentResponse (String razorpayOrderId,String razorpayPaymentId,String razorpaySignature){}

