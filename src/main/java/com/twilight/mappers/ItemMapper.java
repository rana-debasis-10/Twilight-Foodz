package com.twilight.mappers;

import com.twilight.dataTransferObjects.ItemR;
import com.twilight.objects.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ItemMapper {
    @Mapping(target ="id",ignore =true)
    @Mapping(target ="order",ignore =true)
    @Mapping(target ="subtotal",ignore =true)
    public Item toItem (ItemR itemR);

    @Mapping(target ="item.id",ignore =true)
    @Mapping(target ="item.order",ignore =true)
    @Mapping(target ="item.subtotal",ignore =true)

    public ItemR toDto(Item item);
}
