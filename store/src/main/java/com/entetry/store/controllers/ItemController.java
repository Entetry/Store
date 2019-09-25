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

    @GetMapping("/items")
    public List<ItemDto> getAllItems() {
        return itemService.getAllItems();
    }

    @PostMapping("/items")
    public void create(@RequestBody ItemDto itemDto) {
        try {
            itemService.create(itemDto);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage(),e);
        }
    }

    @DeleteMapping("/items/{id}")
    public void delete(@PathVariable String id) {
        try {
            itemService.delete(id);
        } catch (ItemNotFoundException exc) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exc.getMessage(), exc);
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage(),e);
        }
    }

    @PutMapping("/items")
    public void update(@RequestBody ItemDto itemDto) {
        try {
            itemService.update(itemDto);
        } catch (ItemNotFoundException exc) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exc.getMessage(), exc);
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage(),e);
        }
    }

    @GetMapping("/items/subcategory")
    public List<SubcategoryDto> getAllSubcategories() {
        return itemService.getAllSubcategories();
    }

    @PostMapping("items/size")
    public void addSizeToItem(@RequestBody ItemSizeDto itemSizeDto) {
        try {
            itemService.addSizeToItem(itemSizeDto);
        } catch (ItemNotFoundException exc) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exc.getMessage(), exc);
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage(),e);
        }
    }
}
