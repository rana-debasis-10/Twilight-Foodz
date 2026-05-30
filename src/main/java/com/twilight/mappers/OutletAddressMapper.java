package com.twilight.mappers;

import com.twilight.dataTransferObjects.AddressR;
import com.twilight.objects.OutletAddress;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OutletAddressMapper {

    @Mapping(target ="outlet",ignore =true)
    @Mapping(target ="id",ignore =true)
    public OutletAddress toAddress(AddressR address);


    @Mapping(target ="address.outlet",ignore =true)
    @Mapping(target ="address.id",ignore =true)
    public AddressR toAddressR (OutletAddress address);

}
