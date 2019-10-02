package com.entetry.storecommon.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ItemDto implements Serializable {
    @JsonProperty
    private Long id;
    @JsonProperty
    private String name;
    @JsonProperty
    private Date publishDate;
    @JsonProperty
    private String itemStatus;
    @JsonProperty
    private DesignerDto designer;
    @JsonProperty
    private BigDecimal price;
    @JsonProperty
    private SubcategoryDto subcategory;
    @JsonProperty
    private String sex;
    @JsonProperty
    private List<ImageDto> images = new ArrayList<>();
    private List<ItemSizeDto> itemSizes = new ArrayList<>();

        public List<ItemSizeDto> getItemSizes() {
        return itemSizes;
    }

    public void setItemSizes(List<ItemSizeDto> itemSizes) {
        this.itemSizes = itemSizes;
    }

    public List<ImageDto> getImages() {
        return images;
    }

    public void setImages(List<ImageDto> images) {
        this.images = images;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public String getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(String itemStatus) {
        this.itemStatus = itemStatus;
    }

    public DesignerDto getDesigner() {
        return designer;
    }

    public void setDesigner(DesignerDto designer) {
        this.designer = designer;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public SubcategoryDto getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(SubcategoryDto subcategory) {
        this.subcategory = subcategory;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
