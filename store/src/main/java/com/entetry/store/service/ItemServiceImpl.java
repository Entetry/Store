package com.entetry.store.service;

import com.entetry.store.entity.Item;
import com.entetry.store.entity.Size;
import com.entetry.store.exception.ItemNotFoundException;
import com.entetry.store.exception.SizeNotFoundException;
import com.entetry.store.mapper.ItemMapper;
import com.entetry.store.mapper.SubcategoryMapper;
import com.entetry.store.persistense.ImageRepository;
import com.entetry.store.persistense.ItemRepository;
import com.entetry.store.persistense.SizeRepository;
import com.entetry.store.persistense.SubcategoryRepository;
import com.entetry.storecommon.dto.ItemDto;
import com.entetry.storecommon.dto.ItemSizeDto;
import com.entetry.storecommon.dto.SubcategoryDto;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ItemServiceImpl {
    private final ItemRepository itemRepository;
    private final SizeRepository sizeRepository;
    private final SubcategoryRepository subcategoryRepository;
    private final ItemMapper itemMapper;
    private final SubcategoryMapper subcategoryMapper;
    private final ImageRepository imageRepository;
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ItemServiceImpl.class);

    public ItemServiceImpl(SubcategoryMapper subcategoryMapper, ItemRepository itemRepository,
                           SizeRepository sizeRepository, SubcategoryRepository subcategoryRepository,
                           ItemMapper itemMapper, ImageRepository imageRepository) {
        this.itemRepository = itemRepository;
        this.sizeRepository = sizeRepository;
        this.subcategoryRepository = subcategoryRepository;
        this.itemMapper = itemMapper;
        this.subcategoryMapper = subcategoryMapper;
        this.imageRepository = imageRepository;
    }

    @Transactional
    public void create(ItemDto itemDto) {
        try {
            itemRepository.save(itemMapper.toItem(itemDto));
        } catch (Exception e) {
            LOGGER.error("an exception occurred!", e);
            throw e;
        }
    }

    @Transactional
    public void update(ItemDto itemDto) {
        Item item = itemRepository.findById(itemDto.getId()).orElseThrow(ItemNotFoundException::new);
        Item updatedItem = itemMapper.toItem(itemDto);
        try {
            itemRepository.save(updatedItem);
        } catch (Exception e) {
            LOGGER.error("an exception occured!", e);
            throw e;
        }
    }

    @Transactional
    public void delete(String id) {
        Item item = itemRepository.findById(Long.parseLong(id)).orElseThrow(ItemNotFoundException::new);
        try {
            itemRepository.delete(item);
        } catch (Exception e) {
            LOGGER.error("an exception occured!", e);
            throw e;
        }
    }

    public List<ItemDto> getAllItems() {
        return StreamSupport.stream(itemRepository.findAll().spliterator(), false).map(itemMapper::toItemDto).collect(Collectors.toList());
    }

    public ItemDto getItemByName(String name) {
        return itemMapper.toItemDto(itemRepository.getItemByName(name).orElseThrow(ItemNotFoundException::new));
    }

    @Transactional
    public void addSizeToItem(ItemSizeDto itemSizeDto) {
        if (itemSizeDto.getQuantity() > 0) {
            Item item = itemRepository.findById(itemSizeDto.getItem().getId()).orElseThrow(ItemNotFoundException::new);
            Size size = sizeRepository.findById(itemSizeDto.getSize().getId()).orElseThrow(SizeNotFoundException::new);
            item.addSize(size, itemSizeDto.getQuantity());
            try {
                itemRepository.save(item);
                sizeRepository.save(size);
            } catch (Exception e) {
                LOGGER.error("an exception occured!", e);
                throw e;
            }
        }
    }

    public List<SubcategoryDto> getAllSubcategories() {
        return StreamSupport.stream(subcategoryRepository.findAll().spliterator(), false).map(subcategoryMapper::toSubcategoryDto).collect(Collectors.toList());
    }
}
