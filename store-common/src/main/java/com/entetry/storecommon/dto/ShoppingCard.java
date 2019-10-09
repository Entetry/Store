package com.entetry.storecommon.dto;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ShoppingCard implements Serializable {
    private List<ItemDto> items = new ArrayList<>();

    public List<ItemDto> getItems() {
        return items;
    }

    public void setItems(List<ItemDto> items) {
        this.items = items;
    }

    public void addItem(ItemDto itemDto) {
        this.items.add(itemDto);
    }

    public void deleteItem(ItemDto itemDto) {
        this.items.remove(itemDto);
    }
}
