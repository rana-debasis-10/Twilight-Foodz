package com.twilight.serviceImpls;

import com.twilight.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

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
        String otp = generateOtp();
        redis.opsForValue().set(mobNo, otp);

        headers.set("number", mobNo);
        headers.set("otp", otp);
        headers.set("key", apiKey);

        HttpEntity<Void> request =
                new HttpEntity<>(headers);

        ResponseEntity<String> response =
                restTemplate.exchange(
                        endpointUrl + "/send-sms",
                        HttpMethod.POST,
                        request,
                        String.class
                );
        response.getStatusCode().is2xxSuccessful();

    }

    @Override
    public boolean verifyOtp(String mobNo, String otp) {
       return (redis.opsForValue().get(mobNo)).equals(otp);
    }

    private String generateOtp() {
        Random random = new Random();
        int number = random.nextInt(100000,999999);
        return String.valueOf(number);
    }

}
