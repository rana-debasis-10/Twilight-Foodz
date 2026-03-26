package com.twilight.apis;

import com.twilight.annotations.MobileNumber;
import com.twilight.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageApi {
    @Autowired
    private MessageService messageService;
    @GetMapping("/send/{mobNo}")
    String sendOtp(@PathVariable String mobNo){
        System.out.println("sendOtp");
        messageService.sendOtp(mobNo);
        return "success";
    }
}
