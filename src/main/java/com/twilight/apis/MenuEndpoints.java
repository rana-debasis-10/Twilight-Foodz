package com.twilight.apis;

import com.twilight.dataTransferObjects.ProductR;
import com.twilight.exceptions.UnauthorizedException;
import com.twilight.mappers.ProductMapper;
import com.twilight.objects.Product;
import com.twilight.objects.Restaurant;
import com.twilight.services.MenuService;
import com.twilight.services.StorageService;
import com.twilight.utils.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/menu")
public class MenuEndpoints {
    @Autowired
    ProductMapper productMapper;
    @Autowired
    MenuService menuService;
    @Autowired
    UserContext userContext;
    @Autowired
    StorageService storageService;
    @PostMapping(
            value = "/add",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public void addProduct(
            @RequestPart("product") ProductR productR,
            @RequestPart("file") MultipartFile image) throws IOException {

        String mobNo= userContext.getMobile_Number();
        String imageString = storageService.upload(image,"products");
        Product product = productMapper.toProduct(productR);
        product.setImage(imageString);
        menuService.addProduct(mobNo,product);
    }
    @PostMapping("/update/price")
    public void updateProduct(@RequestParam(name= "fId")String foodId, @RequestParam(name= "price") Double price ){
        String outletId = userContext.getEstablishment();
        menuService.overrideFoodPrice(outletId,foodId);
    }
}
