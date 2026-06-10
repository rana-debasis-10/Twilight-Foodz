package com.twilight.apis;

import com.twilight.dataTransferObjects.ProductR;

import com.twilight.mappers.ProductMapper;
import com.twilight.objects.Product;
import com.twilight.services.MenuService;
import com.twilight.services.StorageService;
import com.twilight.utils.UserContext;
import com.twilight.validators.ImageValidator;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
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
    public void addProducts(
            @RequestPart("products") List<ProductR> products,
            @RequestPart("file") List<MultipartFile> images
    ) throws IOException, ChangeSetPersister.NotFoundException {
        if(products.size()==images.size()){
            throw new BadRequestException("Must have equal number of images as products");
        }
        if(!imageValidator.validateImages(images)){
            throw new BadRequestException("Invalid File Type");
        }
        String mobNo = userContext.getMobile_Number();

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
                                + product.imageFileName()
                );
            }

            String objectName =
                    storageService.upload(image,"products");

            Product productDb = productMapper.toProduct(product);

            productsDb.add(productDb);
        }
        menuService.addProducts(mobNo,productsDb);
    }
    public void addProduct(@RequestPart("products") ProductR product,
                           @RequestPart("file") MultipartFile image) throws IOException {

       if(!imageValidator.validateImage(image))
           throw new BadRequestException();

    }
}
