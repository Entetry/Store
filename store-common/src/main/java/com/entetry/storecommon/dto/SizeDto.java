package com.entetry.storecommon.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SizeDto implements Serializable{
    @JsonProperty
    private Long id;
    @JsonProperty
    private String size;
    @JsonProperty
    private List<ItemSizeDto> itemSizes = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public List<ItemSizeDto> getItemSizes() {
        return itemSizes;
    }

    public void setItemSizes(List<ItemSizeDto> itemSizes) {
        this.itemSizes = itemSizes;
    }
}
