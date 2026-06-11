package com.twilight.apis;

import com.twilight.annotations.MobileNumber;
import com.twilight.exceptions.InvalidTypeException;
import com.twilight.exceptions.UnauthorizedException;
import com.twilight.services.JwtService;
import com.twilight.services.MessageService;
import com.twilight.types.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthEndpoints {
    @Autowired
    MessageService messageService;
    @Autowired
    JwtService jwtService;
    @GetMapping("/login")
    @Validated
    boolean login(@RequestParam(name = "m",required = true) @MobileNumber String mobNo){
        messageService.sendOtp(mobNo);
        return true;
    };

    @PostMapping ("/verify")
    @Validated
    String verify(
            @RequestParam(name = "m") @MobileNumber String mobNo,
            @RequestParam(name = "v")String otp) throws InvalidTypeException{

        try{
            if(messageService.verifyOtp(mobNo, otp))

                return jwtService.generateToken(mobNo, Role.customer);

            else

                throw new UnauthorizedException("OTP not matching");

        } catch (NumberFormatException e) {
            throw new InvalidTypeException("OTP should be Integer");
        }

    }



}
