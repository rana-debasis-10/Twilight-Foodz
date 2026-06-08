package com.twilight.apis;

import com.twilight.dataTransferObjects.ProductR;
import com.twilight.exceptions.UnauthorizedException;
import com.twilight.mappers.ProductMapper;
import com.twilight.objects.Product;
import com.twilight.objects.Restaurant;
import com.twilight.services.MenuService;
import com.twilight.utils.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/menu")
public class MenuEndpoints {
    @Autowired
    ProductMapper productMapper;
    @Autowired
    MenuService menuService;
    @Autowired
    UserContext userContext;
    @PostMapping("/add")
    public void addProduct(@RequestBody ProductR productR){
        String restaurantId = userContext.getEstablishment();
        if(restaurantId == null){
            throw new UnauthorizedException("Linked Restaurant not found");
        }
        Product product = productMapper.toProduct(productR);
        menuService.addProduct(restaurantId,product);
    }
    @PostMapping("/update/price")
    public void updateProduct(@RequestParam(name= "fId")String foodId, @RequestParam(name= "price") Double price ){
        String outletId = userContext.getEstablishment();
        menuService.overrideFoodPrice(outletId,foodId);
    }
}
