package com.twilight.services;

import java.util.List;
import java.util.Map;

import com.twilight.objects.Order;
import org.springframework.transaction.annotation.Transactional;

public interface OrderService {
    @Transactional
    public Map<String, Object> create(String mobNo, Order order) throws Exception;

    @Transactional
    public List<Order> get(String mobNo, int page);


}
