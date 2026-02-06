package com.twilight.dataTransferObjects.authentication;

import com.twilight.annotations.ValidEmail;
public record LoginDetails(@ValidEmail String email, String password){}
