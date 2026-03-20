package com.twilight.dataTransferObjects.payment;

public record Webhook(String payload, String signature) {}
