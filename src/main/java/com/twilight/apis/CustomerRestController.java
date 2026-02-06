package com.twilight.apis;

import com.twilight.components.security.UserDetailsImpl;
import com.twilight.dataTransferObjects.request.CustomerOrderRequest;
import com.twilight.services.ItemService;
import com.twilight.services.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/api/customer")
@Slf4j
public class CustomerRestController {

    private final OrderService orderService;

    private final ItemService itemService;

    @Autowired
    public CustomerRestController(OrderService orderService, ItemService itemService) {
        this.orderService = orderService;
        this.itemService = itemService;
    }

    @GetMapping("/order")
    public ResponseEntity<?> viewOrder(@RequestParam int page){
        try{
            UserDetailsImpl userDetails = (UserDetailsImpl) Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication()).getPrincipal();
            assert userDetails != null;
            String customerId = userDetails.getId();
            return new ResponseEntity<>(orderService.getOrders(customerId,page), HttpStatus.OK);
        }
        catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/order")
    public ResponseEntity<?> createOrder(@RequestBody CustomerOrderRequest orderRequest){
        try{
            UserDetailsImpl userDetails = (UserDetailsImpl) Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication()).getPrincipal();
            System.out.println("User Details :" + userDetails);
            assert userDetails != null;
            String customerId = userDetails.getId();
            String mobNo = userDetails.getMobNo();
            System.out.println(customerId+" "+mobNo);

            return new ResponseEntity<>(orderService.createOrder(customerId,mobNo,orderRequest), HttpStatus.OK);
        }
        catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/order/items")
    public ResponseEntity<?> viewOrderItems(@RequestParam String order, @RequestParam int page){
        try{
            return new ResponseEntity<>(itemService.getForOrder(order,page), HttpStatus.OK);
        }
        catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
