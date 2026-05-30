package com.twilight.mappers;

import com.twilight.dataTransferObjects.ProductR;
import com.twilight.objects.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")

public interface ProductMapper {
    @Mapping(target="id", ignore =true)
    @Mapping(target="food",ignore =true)
    @Mapping(target= "restaurant",ignore =true)
    @Mapping(target = "image", ignore = true)
    public Product toProduct(ProductR dto);

    @Mapping(target="product.id", ignore =true)
    @Mapping(target="product.food",ignore =true)
    @Mapping(target= "product.restaurant",ignore =true)
    @Mapping(target = "product.image", ignore = true)

    public ProductR toProductR(Product product);
}
