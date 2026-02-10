package com.twilight.apis;

import com.twilight.dataTransferObjects.request.KycRequest;
import com.twilight.dataTransferObjects.request.RestaurantCreateRequest;
import com.twilight.dataTransferObjects.request.VehicleRequest;
import com.twilight.services.OnBoardingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/on-boarding")
public class OnBoardingRestController {

    private final OnBoardingService onBoardingService;

    @Autowired
    public OnBoardingRestController(OnBoardingService onBoardingService) {
        this.onBoardingService = onBoardingService;
    }
    @PostMapping("/kyc")
    public ResponseEntity<?> knowYourCustomer(@RequestBody KycRequest kyc){
        try{
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/merchant/complete")
    public ResponseEntity<?>  completeForMerchant(@RequestBody RestaurantCreateRequest request){
        try{

            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    public ResponseEntity<?> completeDriver(@RequestBody VehicleRequest request){
        try{
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
