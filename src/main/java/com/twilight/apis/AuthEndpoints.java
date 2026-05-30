package com.twilight.apis;

import com.twilight.annotations.MobileNumber;
import com.twilight.exceptions.InvalidTypeException;
import com.twilight.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthEndpoints {
    @Autowired
    MessageService messageService;
    @GetMapping("/login")
    @Validated
    boolean login(@RequestParam(name = "m") @MobileNumber String mobNo){
        messageService.sendOtp(mobNo);
        return true;
    };
    @PostMapping ("/login")
    boolean verify(@RequestParam(name = "m") @MobileNumber String mobNo, @RequestParam(name = "v")String sOtp) throws InvalidTypeException{
        if(sOtp.length()==6){
            try{
                int otp = Integer.parseInt(sOtp);
                return messageService.verifyOtp(mobNo,otp);
            } catch (NumberFormatException e) {
                throw new InvalidTypeException("OTP should be Integer");
            }
        }
        else{
            throw new InvalidTypeException("Invalid OTP Size");
        }
    }
}
