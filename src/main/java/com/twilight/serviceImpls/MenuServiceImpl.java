package com.twilight.serviceImpls;

import com.twilight.objects.*;
import com.twilight.repositories.FoodRepository;
import com.twilight.repositories.OutletRepository;
import com.twilight.repositories.ProductRepository;
import com.twilight.repositories.RestaurantRepository;
import com.twilight.services.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    KafkaTemplate<String,Object> kafkaTemplate;
    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    OutletRepository outletRepository;

    @Autowired
    FoodRepository foodRepository;

    @Autowired
    ProductRepository productRepository;

    public Restaurant findRestaurantByMobNo(String mobNo){
        return restaurantRepository.findByMerchantMobNo(mobNo).orElse(null);
    }

    @Override
    public void addProduct(String mobNo,Product product) {
        Restaurant restaurant = findRestaurantByMobNo(mobNo);
        product.setRestaurant(restaurant);
        product = productRepository.save(product);
        AddProductRequest request = new AddProductRequest(product.getId(),restaurant.getId());
        kafkaTemplate.send("add-product",request);
    }

    @KafkaListener(
            topics = "add-product",
            groupId = "add-product-group"
    )
    public void consume(AddProductRequest request){
        List<Outlet> outlets =outletRepository.findByRestaurantId(request.getRestaurantId());
        List<Food> foods = new ArrayList<>();


        Product product =
                productRepository.findById(
                        request.getProductId()
                ).orElseThrow();

        for (Outlet outlet : outlets) {

            Food food = new Food();

            food.setProduct(product);
            food.setOutlet(outlet);
            food.setAvailable(false);
            food.setPriceOverride(product.getPrice());

            foods.add(food);
        }

        foodRepository.saveAll(foods);
    }

    @Override
    public void overrideFoodPrice(String outletId, String foodId) {

    }
}
