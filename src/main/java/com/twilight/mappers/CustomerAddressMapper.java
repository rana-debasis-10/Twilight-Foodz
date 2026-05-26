package com.twilight.mappers;

import com.twilight.dataTransferObjects.AddressR;
import com.twilight.objects.CustomerAddress;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerAddressMapper {
    @Mapping(target = "customer", ignore = true )
    @Mapping(target = "id",ignore =true)
    public CustomerAddress toAddress(AddressR address);

    @Mapping(target = "customerAddress.customer", ignore = true )
    @Mapping(target = "customerAddress.id",ignore =true)
    public AddressR toAddressR(CustomerAddress customerAddress);
}
