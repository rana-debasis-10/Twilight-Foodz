package com.twilight.mappers;

import com.twilight.dataTransferObjects.RestaurantR;
import com.twilight.objects.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {
    @Mapping(target = "merchant", ignore = true)
    @Mapping(target = "outlet" ,ignore = true )
    @Mapping(target = "products", ignore =true)
    @Mapping(target = "id", ignore =true)
    @Mapping(target = "image", ignore =true)
    @Mapping(target = "menuAdded", expression = "java(false)")
    Restaurant toRestaurant(RestaurantR restaurant);
}
