package com.twilight.endPoints;


import com.twilight.dataTransferObjects.CustomerR;
import com.twilight.dataTransferObjects.OrderR;
import com.twilight.dataTransferObjects.Payment;
import com.twilight.mappers.CustomerMapper;

import com.twilight.mappers.OrderMapper;
import com.twilight.services.CustomerService;
import com.twilight.services.OrderService;
import com.twilight.services.PaymentService;
import com.twilight.utils.UserContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
public class CustomerEndpoints {
    @Autowired
    CustomerService customerService;
    @Autowired
    OrderService orderService;
    @Autowired
    PaymentService paymentService;
    @Autowired
    CustomerMapper customerMapper;
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    UserContext user;

    @GetMapping("/customer/login")
    @Validated
    @Transactional
    CustomerR loadCustomer() {
        String mobNo = user.getMobNo();
        return customerMapper.toCustomerR(customerService.load(mobNo));
    }

    @PostMapping("/order/create")
    @Validated
    @Transactional
    Map<String, Object> createOrder(@RequestBody OrderR orderDetails) throws Exception {
        return orderService.create(user.getMobNo(),orderMapper.toOrder(orderDetails));
    }

    @PostMapping("/order/verify")
    @Validated
    @Transactional
    void verifyPayment(@RequestBody Payment payment){
        paymentService.verifyPayment(payment);
    }
}
