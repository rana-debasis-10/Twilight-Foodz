package com.twilight.serviceImpls;


import com.twilight.dataTransferObjects.OrderR;
import com.twilight.services.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    public Map<String, Object> createOrder(String mobNo ,OrderR details);

    @Override
    public List<Object> getOrders(String mobNo, int page) {
        return List.of();
    }

    @Override
    public Object updateOrder(String mobNo, String orderId) {
        return null;
    }

    @Override
    public Object createOrder(String mobNo, OrderR order) throws Exception {
        return null;
    }
}
