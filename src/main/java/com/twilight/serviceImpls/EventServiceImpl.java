package com.twilight.serviceImpls;

import com.twilight.dataTransferObjects.MenuUpdateR;
import com.twilight.exceptions.SomethingWentWrongException;
import com.twilight.objects.Food;
import com.twilight.objects.Outlet;
import com.twilight.objects.Product;
import com.twilight.repositories.EventService;
import com.twilight.repositories.FoodRepository;
import com.twilight.repositories.OutletRepository;
import com.twilight.repositories.ProductRepository;
import jakarta.validation.constraints.Size;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class EventServiceImpl implements EventService {
    @Autowired
    KafkaTemplate<String,Object> kafka;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OutletRepository outletRepository;

    @Autowired
    FoodRepository foodRepository;

    @Override
    public  void send(String topic, Object event) throws SomethingWentWrongException {
        try {
            kafka.send(topic,event);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new SomethingWentWrongException("");
        }
    }
    @KafkaListener(
            topics = "Menu-Update",
            groupId = "product-consumer"
    )
    public void productConsumer(MenuUpdateR request){
        try {
            Product product  = productRepository
                    .findById(request.productId())
                    .orElse(null);
            List<Outlet> outlets = outletRepository
                    .findByRestaurantId(
                            request.restaurantId()
                    );

            if(product==null || outlets.isEmpty())
                return;
            List<Food> foods= new ArrayList<>();
            outlets.forEach(outlet->{
                Food food = new Food();
                food.setProduct(product);
                food.setOutlet(outlet);
                foods.add(food);
            });
            foodRepository.saveAll(foods);
        } catch (Exception e) {
            log.error(e.getMessage());
        }

    }

}
