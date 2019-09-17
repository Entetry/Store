package com.entetry.store.service;

import com.entetry.store.entity.Item;
import com.entetry.store.entity.ItemSize;
import com.entetry.store.entity.Size;
import com.entetry.store.exception.ItemNotFoundException;
import com.entetry.store.exception.SizeNotFoundException;
import com.entetry.store.mapper.ItemMapper;
import com.entetry.store.persistense.ItemRepository;
import com.entetry.store.persistense.SizeRepository;
import com.entetry.store.persistense.SubcategoryRepository;
import com.entetry.storecommon.dto.ItemDto;
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
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ItemServiceImpl.class);
    public ItemServiceImpl(ItemRepository itemRepository,SizeRepository sizeRepository,SubcategoryRepository subcategoryRepository,ItemMapper itemMapper){
        this.itemRepository=itemRepository;
        this.sizeRepository=sizeRepository;
        this.subcategoryRepository=subcategoryRepository;
        this.itemMapper=itemMapper;
    }
    @Transactional
    public void create(ItemDto itemDto){
        try{
            itemRepository.save(itemMapper.toItem(itemDto));
        }
        catch (Exception e){
            LOGGER.error("an exception occurred!", e);
        }
    }
    @Transactional
    public void update(ItemDto itemDto){
        Item item = itemRepository.findById(itemDto.getId()).orElseThrow(ItemNotFoundException::new);
        Item updatedItem= itemMapper.toItem(itemDto);
        try {
            itemRepository.save(updatedItem);
        } catch (Exception e) {
            LOGGER.error("an exception occured!", e);
        }
    }
    @Transactional
    public void delete(ItemDto itemDto){
        try {
            itemRepository.delete(itemMapper.toItem(itemDto));
        } catch (Exception e){
            LOGGER.error("an exception occured!", e);
        }
    }
    public List<ItemDto> getAllItems(){
      return StreamSupport.stream(itemRepository.findAll().spliterator(),false).map(itemMapper::toItemDto).collect(Collectors.toList());
    }
    @Transactional
    public void addSizeToItem(ItemSize itemSize) {
        if (itemSize.getQuantity() > 0) {
            Item item = itemRepository.findById(itemSize.getItem().getId()).orElseThrow(ItemNotFoundException::new);
            Size size = sizeRepository.findById(itemSize.getSize().getId()).orElseThrow(SizeNotFoundException::new);
            item.addSize(size);
            try {
                itemRepository.save(item);
            }
            catch (Exception e){
                LOGGER.error("an exception occured!", e);
            }
        }
    }
}
