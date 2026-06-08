package com.twilight.services;

import com.twilight.objects.Food;
import com.twilight.objects.Product;

public interface MenuService {
    void addProduct(String mobNo, Product product);
    void overrideFoodPrice(String outletId, String foodId);
}
