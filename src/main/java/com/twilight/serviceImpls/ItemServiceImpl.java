package com.twilight.serviceImpls;

import com.twilight.repositories.ItemRepository;
import com.twilight.services.AuthService;
import com.twilight.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;

public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemRepository itemRepository;
    @Override
    public List<Object> getItems(String orderId) {
        return Collections.singletonList(itemRepository.findAllByOrderId(orderId));
    }
}
