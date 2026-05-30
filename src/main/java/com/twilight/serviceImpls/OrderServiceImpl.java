package com.twilight.serviceImpls;


import com.twilight.exceptions.UnauthorizedException;
import com.twilight.objects.Customer;
import com.twilight.objects.Order;
import com.twilight.repositories.CustomerRepository;
import com.twilight.repositories.OrderRepository;
import com.twilight.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.RedisTemplate;


import java.util.List;
import java.util.Optional;

public class OrderServiceImpl implements OrderService {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    RedisTemplate<String, Object> redis;
    OrderRepository orderRepository;
    @Autowired


    @Override
    public void createOrder(String mobNo , Order order){
        Optional<Customer> optionalCustomer = customerRepository.findById(mobNo);
        if(optionalCustomer.isPresent()){
            Customer customer = optionalCustomer.get();
            customer.getOrders().add(order);
            order.setCustomer(customer);
        }
        else
            throw new UnauthorizedException("User does not exist");
    };

    @Override
    @Cacheable(value = "products")
    public List<Order> getOrders(String mobNo, int page) {
        return orderRepository.findByCustomerMobNo(mobNo, PageRequest.of(page,5) );
    }





}
