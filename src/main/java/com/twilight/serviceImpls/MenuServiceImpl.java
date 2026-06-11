package com.twilight.serviceImpls;

import com.twilight.objects.*;
import com.twilight.repositories.FoodRepository;
import com.twilight.repositories.OutletRepository;
import com.twilight.repositories.ProductRepository;
import com.twilight.repositories.RestaurantRepository;
import com.twilight.services.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
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
    ProductRepository productRepository;

    @Autowired
    OutletRepository outletRepository;

    @Autowired
    FoodRepository foodRepository;


    public Restaurant findRestaurantByMobNo(String mobNo) throws ChangeSetPersister.NotFoundException {
        return restaurantRepository.findByMerchantMobNo(mobNo).orElseThrow(ChangeSetPersister
                .NotFoundException::new);
    }

    @Override
    public void addProducts(String mobNo, List<Product> products) throws ChangeSetPersister.NotFoundException {
        Restaurant restaurant = findRestaurantByMobNo(mobNo);
        products.forEach(product -> {
            product.setRestaurant(restaurant);
        });
        productRepository.saveAll(products);
    }
    @Override
    public void addProduct(String mobNo, Product product) throws ChangeSetPersister.NotFoundException {
        Restaurant restaurant = findRestaurantByMobNo(mobNo);
        product.setRestaurant(restaurant);
        product = productRepository.save(product);
        AddProductRequest request = new AddProductRequest(product.getId(),restaurant.getId());
        kafkaTemplate.send("new-product",request);
    }
    @KafkaListener(
            topics = "new-product",
            groupId = "new-product-consumer"
    )
    public void productConsumer(AddProductRequest request){
        List<Outlet> outlets = outletRepository.findByRestaurantId(request.getRestaurantId());
        Product product  = productRepository.findById(request.getProductId()).orElse(null);
        if(product==null){
            System.out.println("\n\nProduct Empty\n\n");
            return;
        }
        List<Food> foods= new ArrayList<>();
        outlets.forEach(outlet->{
            Food food = new Food();
            food.setProduct(product);
            food.setOutlet(outlet);
            foods.add(food);
        });
        foodRepository.saveAll(foods);

    }
    @Override
    public void overrideFoodPrice(String outletId, String foodId) {

    }

    @Override
    public boolean checkForMenuAdded(String mobNo) throws ChangeSetPersister.NotFoundException {
        Restaurant restaurant = findRestaurantByMobNo(mobNo);
        return restaurant.isMenuAdded();
    }
}
