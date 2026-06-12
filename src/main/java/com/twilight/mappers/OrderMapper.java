package com.twilight.mappers;

import com.twilight.dataTransferObjects.OrderR;
import com.twilight.objects.Order;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring",
        uses = {
                ItemMapper.class,
                OrderAddressMapper.class
        }
)
public interface OrderMapper {

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "deliveryMobNo", source = "deliveryMobNo")
    @Mapping(target = "paymentMethod", source = "paymentMethod")
    @Mapping(target = "deliveryAddress", source = "address")
    @Mapping(target = "items", source = "foods")
    @Mapping(target = "outletId" ,source = "outletId")
    @Mapping(
            target = "createdAt",
            expression = "java(java.time.LocalDateTime.now())"
    )
    Order toOrder(OrderR orderR);
}