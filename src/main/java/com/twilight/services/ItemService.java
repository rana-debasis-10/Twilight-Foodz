package com.twilight.services;

import com.twilight.components.database.CustomerOrder;
import com.twilight.components.database.Food;
import com.twilight.components.database.Item;
import com.twilight.dataTransferObjects.request.CustomerOrderRequest;
import com.twilight.dataTransferObjects.request.ItemRequest;
import com.twilight.dataTransferObjects.response.component.FoodResponse;
import com.twilight.dataTransferObjects.response.component.ItemResponse;
import com.twilight.repositories.ItemRepo;
import com.twilight.repositories.OutletFoodRepo;
import jakarta.transaction.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ItemService {
    private final OutletFoodRepo outletFoodRepo;
    private final ItemRepo itemRepo;

    @Autowired
    public ItemService(OutletFoodRepo outletFoodRepo, ItemRepo itemRepo) {
        this.outletFoodRepo = outletFoodRepo;
        this.itemRepo = itemRepo;
    }


    @Transactional
    public void addItemsToOrder(List<ItemRequest> requests, CustomerOrder order) throws Exception {

        List<String> foodIds = requests.stream()
                .map(ItemRequest::foodId)
                .toList();
        List<FoodResponse> foods = outletFoodRepo.findAllByIds(foodIds);

        Map<String, FoodResponse> foodMap = foods.stream()
                .collect(Collectors.toMap(FoodResponse::id, f -> f));

        List<Item> items = new ArrayList<>();

        Double total = 0.0;

        for (ItemRequest req : requests) {
            FoodResponse foodResponse = foodMap.get(req.foodId());

            Item item = new Item(foodResponse, req.quantity());
            item.setOrder(order);
            items.add(item);
            total+=item.getSubtotal();
        }
        order.setItems(items);
        order.setTotal(total);
    }

    @Transactional
    public List<ItemResponse> getForOrder(String orderId, int page){
        return itemRepo.findAllByOrderId(orderId,PageRequest.of(page,20))
                    .getContent()
                    .stream()
                    .map(ItemResponse::new)
                    .toList();
    }

}
