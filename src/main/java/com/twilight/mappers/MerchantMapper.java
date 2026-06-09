package com.twilight.mappers;

import com.twilight.dataTransferObjects.MerchantR;
import com.twilight.objects.Merchant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MerchantMapper {
    @Mapping(target = "restaurant", ignore = true)
    @Mapping(target = "deliveryMobNo", ignore = true)
    Merchant toMerchant(MerchantR merchantR);

}
