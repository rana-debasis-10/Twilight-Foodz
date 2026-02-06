package com.twilight.dataTransferObjects.response.payment;

public record RazorpayOrderResponse (String OrderId,int amount,String currency,String key,String receipt){}
