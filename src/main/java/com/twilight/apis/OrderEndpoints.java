package com.twilight.apis;

import java.util.HashMap;
import java.util.Map;

import com.twilight.dataTransferObjects.OrderR;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderEndpoints {
    @PostMapping("/order")
    public Map<String,Object> order(@RequestBody(required = true) OrderR orderDetails) {
        return null;
    }

}
