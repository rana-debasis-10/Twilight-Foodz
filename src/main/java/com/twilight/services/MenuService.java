package com.twilight.services;

import com.twilight.annotations.MobileNumber;
import com.twilight.exceptions.NotFoundException;
import com.twilight.exceptions.UnAuthorizedException;
import com.twilight.objects.Product;
import software.amazon.awssdk.annotations.NotNull;

import java.util.List;

public interface MenuService {
    void addAll(String mobNo, List<Product> products) throws NotFoundException;

    void add(String mobNo, Product product) throws NotFoundException;

    void checkForMenuAdded(@MobileNumber @NotNull String mobNo) throws UnAuthorizedException;
}
