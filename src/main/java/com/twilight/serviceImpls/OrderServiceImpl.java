package com.twilight.serviceImpls;


import com.razorpay.RazorpayException;
import com.twilight.dataTransferObjects.FoodPrice;
import com.twilight.exceptions.UnauthorizedException;
import com.twilight.objects.Customer;
import com.twilight.objects.Food;
import com.twilight.objects.Item;
import com.twilight.objects.Order;
import com.twilight.repositories.CustomerRepository;
import com.twilight.repositories.FoodRepository;
import com.twilight.repositories.OrderRepository;
import com.twilight.services.OrderService;
import com.twilight.services.PaymentService;
import com.twilight.types.DeliveryStatus;
import com.twilight.types.PaymentMethod;
import com.twilight.types.PaymentStatus;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    RedisTemplate<String, Object> redis;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    FoodRepository foodRepository;

    @Autowired
    PaymentService paymentService;



    @Override
    @Transactional
    public Map<String, Object > createOrder(String mobNo, Order order) throws BadRequestException, RazorpayException {

        Customer customer = customerRepository
                .findById(mobNo)
                .orElseThrow(
                        () -> new UnauthorizedException(
                                "User does not exist"
                        )
                );


        Map<String,Double> foods = validateAndGetFoodMap(order.getOutletId(),order.getItems());
        double total = 0.00;

        for (Item item : order.getItems()) {
            double price = foods.get(item.getFoodId());
            double subtotal = price * item.getQuantity();
            item.setPrice(price);
            item.setSubtotal(subtotal);
            item.setOrder(order);
            total += subtotal;
        }

        order.setCustomer(customer);
        order.setTotal(total);
        order.setPaymentStatus(
                PaymentStatus.pending
        );
        order.setDeliveryStatus(
                DeliveryStatus.ordered
        );
        String receipt = generateReceiptId();

        Map<String, Object> response ;

        if(order.getPaymentMethod() == PaymentMethod.cash_on_delivery){
            response = new HashMap<>();
            response.put("order-creation-complete",Boolean.TRUE);
        }
        else{
            response =paymentService.createPayment(total, "IND", receipt);
            order.setRazorpayOrderId((String) response.get("id"));
            response.put("order-creation-complete",Boolean.FALSE);

        }
        orderRepository.save(order);

        return response;
    }

    @Override
    @Cacheable(value = "orders")
    public List<Order> getOrders(String mobNo, int page) {
        return orderRepository.findByCustomerMobNo(mobNo, PageRequest.of(page,5) );
    }

    private List<FoodPrice> getFoods(String outletId , List<String> foodIds){
        return foodRepository.findFoodPrices(outletId,foodIds);
    }
    private String generateReceiptId() {

        return "twlt_" +
                UUID.randomUUID()
                        .toString()
                        .replace("-", "")
                        .substring(0, 15);
    }
    private Map<String, Double> validateAndGetFoodMap(String outletId ,List<Item> items) throws BadRequestException{
        List<String> foodIds = items
                .stream()
                .map(Item::getFoodId)
                .toList();

        List<FoodPrice> foods = getFoods(outletId, foodIds);

        if (foods.size() != foodIds.size()) {
            throw new BadRequestException(
                    "Some foods are unavailable"
            );
        }

        return
                foods.stream()
                        .collect(
                                Collectors.toMap(
                                        FoodPrice::foodId,
                                        FoodPrice::price
                                )
                        );
    }
}
