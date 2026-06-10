package com.twilight.services;

import com.twilight.objects.Food;
import com.twilight.objects.Product;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface MenuService {
    void addProducts(String mobNo, List<Product> products) throws ChangeSetPersister.NotFoundException;

    void addProduct(String mobNo, Product product) throws ChangeSetPersister.NotFoundException;

    void overrideFoodPrice(String outletId, String foodId);
}
