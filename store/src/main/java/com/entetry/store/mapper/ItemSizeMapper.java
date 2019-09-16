package com.entetry.store.mapper;

import com.entetry.store.entity.Item;
import com.entetry.store.entity.ItemSize;
import com.entetry.store.entity.Size;
import com.entetry.storecommon.dto.ItemDto;
import com.entetry.storecommon.dto.ItemSizeDto;
import com.entetry.storecommon.dto.SizeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ItemSizeMapper {
    private final DesignerMapper designerMapper;
    private final SubcategoryMapper subcategoryMapper;
    private final ImageMapper imageMapper;
    @Autowired
    public ItemSizeMapper(DesignerMapper designerMapper,SubcategoryMapper subcategoryMapper,ImageMapper imageMapper){
        this.designerMapper=designerMapper;
        this.subcategoryMapper=subcategoryMapper;
        this.imageMapper=imageMapper;
    }
    public ItemSizeDto toItemSizeDto(ItemSize itemSize){
        ItemSizeDto itemSizeDto = new ItemSizeDto();
        ItemDto itemDto = new ItemDto();
        itemDto.setId(itemSize.getItem().getId());
        itemDto.setName(itemSize.getItem().getName());
        itemDto.setPrice(itemSize.getItem().getPrice());
        itemDto.setPublishDate(itemSize.getItem().getPublishDate());
        itemDto.setSex(itemSize.getItem().getSex());
        itemDto.setSubcategory(subcategoryMapper.toSubcategoryDto(itemSize.getItem().getSubcategory()));
        itemDto.setDesigner(designerMapper.toDesignerDto(itemSize.getItem().getDesigner()));
        itemDto.setItemStatus(itemSize.getItem().getItemStatus());
        itemDto.setImages(itemSize.getItem().getImages().stream().map(imageMapper::toImageDto).collect(Collectors.toList()));
        itemSizeDto.setItem(itemDto);
        SizeDto sizeDto = new SizeDto();
        sizeDto.setId(itemSize.getSize().getId());
        sizeDto.setSize(itemSize.getSize().getSize());
        itemSizeDto.setSize(sizeDto);
        itemSizeDto.setQuantity(itemSize.getQuantity());
    return itemSizeDto;
    }
    public ItemSize toItemSize(ItemSizeDto itemSizeDto){
        ItemSize itemSize = new ItemSize();
        Item item = new Item();
        item.setId(itemSizeDto.getItem().getId());
        item.setName(itemSizeDto.getItem().getName());
        item.setPrice(itemSizeDto.getItem().getPrice());
        item.setPublishDate(itemSizeDto.getItem().getPublishDate());
        item.setSex(itemSizeDto.getItem().getSex());
        item.setSubcategory(subcategoryMapper.toSubcategory(itemSizeDto.getItem().getSubcategory()));
        item.setDesigner(designerMapper.toDesigner(itemSizeDto.getItem().getDesigner()));
        item.setItemStatus(itemSizeDto.getItem().getItemStatus());
        item.setImages(itemSizeDto.getItem().getImages().stream().map(imageMapper::toImage).collect(Collectors.toList()));
        itemSize.setItem(item);
        Size size = new Size();
        size.setId(itemSizeDto.getSize().getId());
        size.setSize(itemSizeDto.getSize().getSize());
        itemSize.setSize(size);
        itemSize.setQuantity(itemSizeDto.getQuantity());
        return itemSize;
    }
}
