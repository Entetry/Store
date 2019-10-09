package com.entetry.storecommon.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class ItemSizeDto implements Serializable {
    @JsonProperty
    private ItemDto item;
    @JsonProperty
    private SizeDto size;
    @JsonProperty
    private int quantity;

    public ItemSizeDto() {
    }

    ;

    public ItemSizeDto(ItemDto item, SizeDto size, int quantity) {
        this.item = item;
        this.size = size;
        this.quantity = quantity;
    }

    public ItemDto getItem() {
        return item;
    }

    public void setItem(ItemDto item) {
        this.item = item;
    }

    public SizeDto getSize() {
        return size;
    }

    public void setSize(SizeDto size) {
        this.size = size;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
