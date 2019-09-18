package com.entetry.store.mapper;

import com.entetry.store.entity.ItemSize;
import com.entetry.store.entity.Size;
import com.entetry.storecommon.dto.SizeDto;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class SizeMapper {
    private final ItemSizeMapper itemSizeMapper;

    public SizeMapper(ItemSizeMapper itemSizeMapper) {
        this.itemSizeMapper = itemSizeMapper;
    }

    public SizeDto toSizeDto(Size size) {
        SizeDto sizeDto = new SizeDto();
        sizeDto.setId(size.getId());
        sizeDto.setItemSizes(size.getItemSizes().stream().map(itemSizeMapper::toItemSizeDto).collect(Collectors.toList()));
        return sizeDto;
    }

    public Size toSize(SizeDto sizeDto) {
        Size size = new Size();
        size.setId(sizeDto.getId());
        size.setSize(sizeDto.getSize());
        size.setItemSizes(sizeDto.getItemSizes().stream().map(itemSizeMapper::toItemSize).collect(Collectors.toList()));
        return size;
    }
}
