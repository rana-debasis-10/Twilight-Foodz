package com.twilight.services;

import java.util.List;

import com.twilight.objects.Order;
import org.springframework.transaction.annotation.Transactional;

public interface OrderService {
    @Transactional
    public void createOrder(String mobNo, Order order) throws Exception;

    @Transactional
    public List<Order> getOrders(String mobNo, int page);

}
