package com.twilight.apis;

import com.twilight.services.*;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/admin")
public class AdminRestController {
    private final OrderService orderService;

    private final CustomerService customerService;

    private final AddressService addressService;

    private final FoodService foodService;

    private final RestaurantService restaurantService;

    @Autowired
    public AdminRestController(OrderService orderService, CustomerService customerService, AddressService addressService, FoodService foodService, RestaurantService restaurantService) {
        this.orderService = orderService;
        this.customerService = customerService;
        this.addressService = addressService;
        this.foodService = foodService;
        this.restaurantService = restaurantService;
    }


    @GetMapping("/orders")
    public ResponseEntity<?> getAllOrder(@NotNull @RequestParam int page){
        try{
            return new ResponseEntity<>(orderService.getAllOrder(page), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/customers")
    public ResponseEntity<?> getAllCustomer(@NotNull @RequestParam int page){
        try{
            return new ResponseEntity<>(customerService.getAll(page), HttpStatus.OK);
        }
        catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/addresses")
    public ResponseEntity<?> getAllAddress(@NotNull @RequestParam int page){
        try{
            return new ResponseEntity<>(addressService.getAll(page), HttpStatus.OK);
        }
        catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/restaurants")
    public ResponseEntity<?> getAllRestaurant(@NotNull @RequestParam int page){
        try{
            return new ResponseEntity<>(restaurantService.getAllRestaurant(page), HttpStatus.OK);
        }
        catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/make-admin")
    public ResponseEntity<?> makeAdmin(@NotNull @RequestParam String id){
        try{
            customerService.superAdmin(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
