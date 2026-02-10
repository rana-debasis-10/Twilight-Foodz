package com.twilight.apis;


import com.twilight.components.security.UserDetailsImpl;
import com.twilight.dataTransferObjects.request.FoodRequest;
import com.twilight.services.MerchantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;


@RestController
@RequestMapping("/api/restaurant")
@Slf4j
public class MerchantController {

    private final MerchantService merchantService;

    public MerchantController(MerchantService merchantService) {
        this.merchantService = merchantService;
    }


    @PostMapping("/food")//Path to be declared
    public ResponseEntity<?> createProduct(@RequestBody List<FoodRequest> request) {
        try{

            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch(Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
