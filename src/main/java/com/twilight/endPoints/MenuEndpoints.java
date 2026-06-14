package com.twilight.endPoints;

import com.twilight.dataTransferObjects.ProductR;
import com.twilight.exceptions.BadRequestException;
import com.twilight.exceptions.NotFoundException;
import com.twilight.mappers.ProductMapper;
import com.twilight.objects.Product;
import com.twilight.services.MenuService;
import com.twilight.services.StorageService;
import com.twilight.utils.UserContext;
import com.twilight.validators.ImageValidator;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    @Autowired
    ImageValidator imageValidator;

    @PostMapping(
            value = "/add",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    @Transactional
    @Validated
    public void addProducts(
            @RequestPart("products") List<ProductR> products,
            @RequestPart("file") List<MultipartFile> images
    ) throws IOException, NotFoundException {

        String mobNo = userContext.getMobNo();


        if(products.size()==images.size() || products.size()>100){
            throw new BadRequestException("Must have equal number of images as products and should be less than 100", "");
        }

        imageValidator.validateImages(images);



        Map<String, MultipartFile> fileMap = images.stream()
                .collect(Collectors.toMap(
                        MultipartFile::getOriginalFilename,
                        Function.identity()
                ));


        List<Product> productsDb = new ArrayList<>();
        for (ProductR product : products) {

            MultipartFile image =
                    fileMap.get(product.imageFileName());

            if (image == null) {
                throw new BadRequestException(
                        "Image not found: "
                                + product.imageFileName(),
                        "");
            }

            String objectName =
                    storageService.upload(image,"products");

            Product productDb = productMapper.toProduct(product);
            productDb.setImage(objectName);

            productsDb.add(productDb);
        }
        menuService.addAll(mobNo,productsDb);
    }


    @PostMapping(
            value ="/add/product",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    @Transactional
    @Validated

    public void addProduct(@RequestPart("products") ProductR productR,
                           @RequestPart("file") MultipartFile image) throws IOException {
        String mobNo = userContext.getMobNo();

       imageValidator.validateImage(image);

       String imageKey = storageService.upload(image,"products");

       Product product = productMapper.toProduct(productR);

       product.setImage(imageKey);

       menuService.add(mobNo,product);

    }
}
