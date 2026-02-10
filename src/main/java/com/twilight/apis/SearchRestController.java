package com.twilight.apis;

import com.twilight.services.FoodService;
import com.twilight.services.RestaurantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/find")
@Slf4j
public class SearchRestController {

    private final FoodService foodService;

    private final RestaurantService restaurantService;

    @Autowired
    public SearchRestController(FoodService foodService, RestaurantService restaurantService) {
        this.foodService = foodService;
        this.restaurantService = restaurantService;
    }

    @GetMapping("/restaurant")
    ResponseEntity<?> findRestaurant(@RequestParam String name, @RequestParam int page){
        try{
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/food")
    ResponseEntity<?> findFood(@RequestParam String name, @RequestParam int page){
        try{
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
