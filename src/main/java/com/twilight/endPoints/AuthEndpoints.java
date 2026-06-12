package com.twilight.endPoints;

import com.twilight.annotations.MobileNumber;
import com.twilight.exceptions.UnAuthorizedException;
import com.twilight.services.JwtService;
import com.twilight.services.MessageService;
import com.twilight.types.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthEndpoints {
    @Autowired
    MessageService messageService;
    @Autowired
    JwtService jwtService;
    @GetMapping("/login")
    @Validated
    void login(@RequestParam(name = "m",required = true) @MobileNumber String mobNo){
        messageService.sendOtp(mobNo);
    };

    @PostMapping ("/verify")
    @Validated
    String verify(
            @RequestParam(name = "m") @MobileNumber String mobNo,
            @RequestParam(name = "o")Integer otp) throws UnAuthorizedException {
        if(messageService.verifyOtp(mobNo, otp))
                return jwtService.generateToken(mobNo, Role.undefined);
        else
                throw new UnAuthorizedException("OTP not matching");

    }



}
