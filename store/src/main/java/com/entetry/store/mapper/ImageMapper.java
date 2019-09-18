package com.entetry.store.mapper;

import com.entetry.store.entity.Image;
import com.entetry.store.entity.Item;
import com.entetry.storecommon.dto.ImageDto;
import com.entetry.storecommon.dto.ItemDto;
import org.springframework.stereotype.Component;

@Component
public class ImageMapper {
    private final SubcategoryMapper subcategoryMapper;

    public ImageMapper(SubcategoryMapper subcategoryMapper) {
        this.subcategoryMapper = subcategoryMapper;
    }

    public ImageDto toImageDto(Image image) {
        ImageDto imageDto = new ImageDto();
        imageDto.setId(image.getId());
        imageDto.setName(image.getName());
        imageDto.setUrl(image.getUrl());
        ItemDto itemDto = new ItemDto();
        itemDto.setId(image.getItem().getId());
        itemDto.setItemStatus(image.getItem().getItemStatus());
        itemDto.setPrice(image.getItem().getPrice());
        itemDto.setSubcategory(subcategoryMapper.toSubcategoryDto(image.getItem().getSubcategory()));
        itemDto.setName(image.getItem().getName());
        imageDto.setItem(itemDto);
        return imageDto;
    }

    public Image toImage(ImageDto imageDto) {
        Image image = new Image();
        image.setId(imageDto.getId());
        image.setName(imageDto.getName());
        image.setUrl(imageDto.getUrl());
        Item item = new Item();
        item.setId(imageDto.getItem().getId());
        item.setItemStatus(imageDto.getItem().getItemStatus());
        item.setPrice(imageDto.getItem().getPrice());
        item.setSubcategory(subcategoryMapper.toSubcategory(imageDto.getItem().getSubcategory()));
        item.setName(imageDto.getItem().getName());
        image.setItem(item);
        return image;
    }
}
