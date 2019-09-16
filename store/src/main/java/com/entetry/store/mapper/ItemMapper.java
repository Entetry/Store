package com.entetry.store.mapper;

import com.entetry.store.entity.Item;
import com.entetry.storecommon.dto.ItemDto;

public class ItemMapper {
    public static ItemDto toItemDto(Item item) {
        ItemDto itemDto = new ItemDto();
        itemDto.setId(item.getId());
        itemDto.setName(item.getName());
        itemDto.setPrice(item.getPrice());
        itemDto.setPublishDate(item.getPublishDate());
        itemDto.setSex(item.getSex());
        itemDto.setSubcategory(SubcategoryMapper.toSubcategoryDto(item.getSubcategory()));
        itemDto.setDesigner(DesignerMapper.toDesignerDto(item.getDesigner()));
        itemDto.setItemStatus(item.getItemStatus());
        return itemDto;
    }

    public static Item toItem(ItemDto itemDto) {
        Item item = new Item();
        item.setId(itemDto.getId());
        item.setName(itemDto.getName());
        item.setDesigner(DesignerMapper.toDesigner(itemDto.getDesigner()));
        item.setItemStatus(itemDto.getItemStatus());
        item.setPrice(itemDto.getPrice());
        item.setPublishDate(itemDto.getPublishDate());
        item.setSex(itemDto.getSex());
        item.setSubcategory(SubcategoryMapper.toSubcategory(itemDto.getSubcategory()));
        return item;
    }
}
