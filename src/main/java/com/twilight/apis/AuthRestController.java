package com.twilight.apis;

import com.twilight.dataTransferObjects.authentication.LoginDetails;
import com.twilight.dataTransferObjects.authentication.BasicUserDetails;
import com.twilight.services.*;
import com.twilight.types.OnBoarder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/auth")
public class AuthRestController {


    private final OnBoardingService onBoardingService;

    private final AuthService authService;
    @Autowired
    public AuthRestController(AuthService authService, OnBoardingService onBoardingService) {
        this.onBoardingService = onBoardingService;
        this.authService = authService;
    }


    /// Register
    @PostMapping("/register")
    @Validated
    public ResponseEntity<?> register(@RequestBody BasicUserDetails basicUserDetails){
        try {
            return new ResponseEntity<>(authService.register(basicUserDetails), HttpStatus.OK);
        }
        catch (Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    /// LoginDetails
    @PostMapping("/login")
    @Validated
    public ResponseEntity<?>login(@RequestBody LoginDetails loginDetails){
        try{
            return new ResponseEntity<>(authService.login(loginDetails), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        }
    }

    /// Register Restaurant with a Customer
    @PostMapping("/on-boarding/start")
    @Validated
    public ResponseEntity<?> startOnboarding(@RequestParam String type, @RequestBody BasicUserDetails basicUserDetails){
        try{
            return new ResponseEntity<>(onBoardingService.startOnBoarding(basicUserDetails,OnBoarder.valueOf(type.toUpperCase())),HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
