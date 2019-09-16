package com.entetry.storecommon.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CategoryDto implements Serializable {
    @JsonProperty
    private Long id;
    @JsonProperty
    private String name;
    @JsonProperty
    private List<SubcategoryDto> subcategories = new ArrayList<>();

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

    public List<SubcategoryDto> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(List<SubcategoryDto> subcategories) {
        this.subcategories = subcategories;
    }
}
