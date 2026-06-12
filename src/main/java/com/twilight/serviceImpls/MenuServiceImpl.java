package com.twilight.serviceImpls;

import com.twilight.dataTransferObjects.MenuUpdateR;
import com.twilight.exceptions.NotFoundException;
import com.twilight.exceptions.SomethingWentWrongException;
import com.twilight.exceptions.UnAuthorizedException;
import com.twilight.objects.*;
import com.twilight.repositories.*;
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
    @Autowired
    EventService eventService;


    public Restaurant findRestaurantByMobNo(String mobNo) throws NotFoundException {
        return restaurantRepository
                .findByMerchantMobNo(mobNo)
                .orElseThrow(
                        ()->new NotFoundException("No Restaurant linked to your mobile number")
                );
    }

    @Override
    public void addAll(String mobNo, List<Product> products) throws NotFoundException {
        Restaurant restaurant = findRestaurantByMobNo(mobNo);
        restaurant.setMenuAdded(true);
        products.forEach(product -> {
            product.setRestaurant(restaurant);
        });
        restaurant.setProducts(products);
        restaurantRepository.save(restaurant);
    }
    @Override
    public void add(String mobNo, Product product) throws NotFoundException , SomethingWentWrongException {
        Restaurant restaurant = findRestaurantByMobNo(mobNo);
        product.setRestaurant(restaurant);
        product = productRepository.save(product);
        MenuUpdateR request = new MenuUpdateR(product.getId(),restaurant.getId());
        eventService.send("Menu-Update",request);
    }

    @Override
    public void checkForMenuAdded(String mobNo) throws UnAuthorizedException {
        Restaurant restaurant = findRestaurantByMobNo(mobNo);
        if(restaurant.isMenuAdded())
            throw new UnAuthorizedException("Menu can be added once only");
    }
}
