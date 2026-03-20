package com.twilight.services;

import java.util.List;
import com.twilight.dataTransferObjects.OrderR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.UUID;

public interface OrderService {
    @Transactional
    public Object createOrder(String mobNo, OrderR order) throws Exception;

    @Transactional
    public List<Object> getOrders(String mobNo, int page);

    @Transactional
    public Object updateOrder(String mobNo,String orderId);
}
