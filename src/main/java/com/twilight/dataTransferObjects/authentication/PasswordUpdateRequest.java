package com.twilight.dataTransferObjects.authentication;

public record PasswordUpdateRequest(String oldPassword,String newPassword) {
}
