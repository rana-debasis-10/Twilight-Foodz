package com.twilight.apis;

import com.twilight.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageApi {
    @Autowired
    private MessageService messageService;
    @GetMapping("/send/{mobNo}")
    String sendOtp(@PathVariable String mobNo){
        messageService.sendOtp(mobNo);
        return "success";
    }
    @GetMapping("/verify")
    @Validated
    String verifyOtp(@RequestParam(name="mobNo") String mobNo ,@RequestParam(name ="otp") String otp){
        if(messageService.checkOtp(mobNo).equals(otp)){
            return "success";
        }
        else
        {
            return "fail";
        }
    }
}
