package com.twilight.serviceImpls;

import com.twilight.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class MessageServiceImpl implements MessageService {

    @Value("${message.key}")
    private String apiKey;
    @Value("${message.endpoint}")
    private String endpointUrl;

    @Autowired
    private RedisTemplate<String,String> redis;

    private final RestTemplate restTemplate = new RestTemplate();
    @Override
    public void sendOtp(String mobNo) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-API-KEY", apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);
        String otp = generateOtp();
        redis.opsForValue().set(mobNo, otp,5, TimeUnit.MINUTES);
        Map<String, String> body = Map.of(
                "Mobile Number", mobNo,
                "Message", "Dear Customer your OTP is " + otp
        );

        HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(endpointUrl, request, String.class);
        response.getStatusCode().is2xxSuccessful();

    }

    @Override
    public String checkOtp(String mobNo) {
       return (String)redis.opsForValue().get(mobNo);
    }

    private String generateOtp() {
        Random random = new Random();
        int number = random.nextInt(100000,999999);
        return String.valueOf(number);
    }

}
