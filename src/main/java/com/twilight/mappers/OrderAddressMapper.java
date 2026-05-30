package com.twilight.mappers;

import com.twilight.dataTransferObjects.AddressR;
import com.twilight.objects.OrderAddress;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderAddressMapper {
    @Mapping(target = "orderAddress.order", ignore = true)
    @Mapping(target = "orderAddress.id",ignore =true)
    public AddressR toAddressR(OrderAddress orderAddress);

    @Mapping(target ="order", ignore =true)
    @Mapping(target = "id",ignore =true)

    OrderAddress toAddress(AddressR address);
}
