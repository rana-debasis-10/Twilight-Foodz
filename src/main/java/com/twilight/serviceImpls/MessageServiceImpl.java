package com.twilight.serviceImpls;

import com.twilight.exceptions.NotFoundException;
import com.twilight.exceptions.SomethingWentWrongException;
import com.twilight.services.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;
import java.util.Random;

@Service
@Slf4j
public class MessageServiceImpl implements MessageService {

    @Value("${message.key}")
    private String apiKey;
    @Value("${message.endpoint}")
    private String endpointUrl;

    @Autowired
    private RedisTemplate<String,String> redis;

    @Autowired
    private RestTemplate restTemplate;
    @Override
    public void sendOtp(String mobNo) throws NotFoundException , SomethingWentWrongException {

        String otp = generateOtp();
        try {
            redis.opsForValue().set(mobNo, otp);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new SomethingWentWrongException("Some Service is unavailable at this moment");
        }

        HttpHeaders headers = new HttpHeaders();

        headers.set("number", mobNo);
        headers.set("otp", otp);
        headers.set("key", apiKey);

        HttpEntity<Void> request =
                new HttpEntity<>(headers);
        ResponseEntity<String> response;

        try{
           response =
                    restTemplate.exchange(
                            endpointUrl + "/send-sms",
                            HttpMethod.POST,
                            request,
                            String.class
                    );
        } catch (RuntimeException e) {
            throw new NotFoundException("This Service is currently under maintenance");
        }
        if(response.getStatusCode().is2xxSuccessful())
            throw new SomethingWentWrongException("SMS server is unable to send OTP currently");

    }

    @Override
    public boolean verifyOtp(String mobNo, Integer otp) {
        try {
            return Objects.equals(redis.opsForValue().get(mobNo), otp.toString());
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new SomethingWentWrongException("Some Service is unavailable at this moment");
        }
    }

    private String generateOtp() {
        Random random = new Random();
        int number = random.nextInt(100000,999999);
        return String.valueOf(number);
    }

}
