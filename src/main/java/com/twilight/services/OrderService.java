package com.twilight.services;

import com.twilight.dataTransferObjects.response.component.CustomerOrderResponse;
import com.twilight.dataTransferObjects.request.CustomerOrderRequest;
import com.twilight.objects.database.Customer;
import com.twilight.objects.database.Order;
import com.twilight.types.Currency;
import com.twilight.types.PaymentMethod;
import com.twilight.repositories.OrderRepo;
import com.twilight.types.PaymentStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
@Slf4j
@Service
public class OrderService {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private ItemService itemService;

    @Autowired
    private PaymentService paymentService;

    /// Making Order
    @Transactional
    public Map<String, Object> createOrder(
            String customerId,
            String mobNo,
            CustomerOrderRequest details
    ) throws Exception {

        String receipt = "twlt_" + UUID.randomUUID()
                .toString()
                .replace("-", "")
                .substring(0, 20);

        Order order = new Order(details, receipt, Currency.INR);

        itemService.addItemsToOrder(details.foods(), order);

        order.setMobNo(details.mobNo() != null ? details.mobNo() : mobNo);

        Map<String, Object> response = new HashMap<>();

        if (details.paymentMethod() != PaymentMethod.CASH_ON_DELIVERY) {
            response = paymentService.createPayment(
                    order.getTotal(),
                    "INR",
                    receipt
            );

            order.setRazorpayOrderId((String) response.get("orderId"));
            order.setPaymentStatus(PaymentStatus.PENDING);
        } else {
            order.setPaymentStatus(PaymentStatus.PENDING);
        }

        Customer customer = customerService.getById(customerId);
        order.setCustomer(customer);
        customer.getOrders().add(order);

        customerService.update(customer);

        return response;
    }

    /// View Orders
    public List<CustomerOrderResponse> getOrders(String customerId, int pageNum){

        return orderRepo.findByCustomerId(customerId,PageRequest.of(pageNum,10))
                .getContent()
                .stream()
                .map(CustomerOrderResponse::new)
                .toList();
    }

    /// For Admin View
    public List<Order> getAllOrder(int pageNum){
        return orderRepo.findAll(PageRequest.of(pageNum, 10)).getContent();
    }

}
