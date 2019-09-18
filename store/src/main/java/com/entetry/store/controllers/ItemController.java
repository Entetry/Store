package com.entetry.store.controllers;

import com.entetry.store.exception.ItemNotFoundException;
import com.entetry.store.service.ItemServiceImpl;
import com.entetry.storecommon.dto.ItemDto;
import com.entetry.storecommon.dto.ItemSizeDto;
import com.entetry.storecommon.dto.SubcategoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class ItemController {
    private final ItemServiceImpl itemService;

    @Autowired
    public ItemController(ItemServiceImpl itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/item")
    public List<ItemDto> getAllUsers() {
        return itemService.getAllItems();
    }

    @PostMapping("/item")
    public void create(@RequestBody ItemDto itemDto) {
        try {
            itemService.create(itemDto);
        } catch (ItemNotFoundException exc) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exc.getMessage(), exc);
        }
    }

    @DeleteMapping("/item")
    public void delete(@RequestBody ItemDto itemDto) {
        try {
            itemService.delete(itemDto);
        } catch (ItemNotFoundException exc) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exc.getMessage(), exc);
        }
    }

    @PutMapping("/item")
    public void update(@RequestBody ItemDto itemDto) {
        try {
            itemService.update(itemDto);
        } catch (ItemNotFoundException exc) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exc.getMessage(), exc);
        }
    }

    @GetMapping("/item/subcategory")
    public List<SubcategoryDto> getAllSubcategories() {
        return itemService.getAllSubcategories();
    }

    @PostMapping("item/size")
    public void addSizeToItem(@RequestBody ItemSizeDto itemSizeDto) {
        try {
            itemService.addSizeToItem(itemSizeDto);
        } catch (ItemNotFoundException exc) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exc.getMessage(), exc);
        }
    }
}
