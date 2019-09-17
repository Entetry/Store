package com.entetry.store.mapper;

import com.entetry.store.entity.Item;
import com.entetry.store.entity.Subcategory;
import com.entetry.storecommon.dto.ItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ItemMapper {
    private final DesignerMapper designerMapper;
    private final SubcategoryMapper subcategoryMapper;
    private final ImageMapper imageMapper;
    private final ItemSizeMapper itemSizeMapper;
     @Autowired
    public ItemMapper(DesignerMapper designerMapper,SubcategoryMapper subcategoryMapper,ImageMapper imageMapper,ItemSizeMapper itemSizeMapper){
        this.designerMapper=designerMapper;
        this.subcategoryMapper=subcategoryMapper;
        this.imageMapper=imageMapper;
        this.itemSizeMapper=itemSizeMapper;
    }
    public  ItemDto toItemDto(Item item) {
        ItemDto itemDto = new ItemDto();
        itemDto.setId(item.getId());
        itemDto.setName(item.getName());
        itemDto.setPrice(item.getPrice());
        itemDto.setPublishDate(item.getPublishDate());
        itemDto.setSex(item.getSex());
        itemDto.setSubcategory(subcategoryMapper.toSubcategoryDto(item.getSubcategory()));
        itemDto.setDesigner(designerMapper.toDesignerDto(item.getDesigner()));
        itemDto.setItemStatus(item.getItemStatus());
        itemDto.setImages(item.getImages().stream().map(imageMapper::toImageDto).collect(Collectors.toList()));
       // itemDto.setItemSizes(item.getItemSizes().stream().map(itemSizeMapper::toItemSizeDto).collect(Collectors.toList()));
        return itemDto;
    }

    public  Item toItem(ItemDto itemDto) {
        Item item = new Item();
        item.setId(itemDto.getId());
        item.setName(itemDto.getName());
        item.setDesigner(designerMapper.toDesigner(itemDto.getDesigner()));
        item.setItemStatus(itemDto.getItemStatus());
        item.setPrice(itemDto.getPrice());
        item.setPublishDate(itemDto.getPublishDate());
        item.setSex(itemDto.getSex());
        item.setSubcategory(subcategoryMapper.toSubcategory(itemDto.getSubcategory()));
        item.setImages(itemDto.getImages().stream().map(imageMapper::toImage).collect(Collectors.toList()));
        //item.setItemSizes(itemDto.getItemSizes().stream().map(itemSizeMapper::toItemSize).collect(Collectors.toList()));
        return item;
    }
}
