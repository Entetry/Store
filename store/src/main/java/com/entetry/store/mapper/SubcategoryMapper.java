package com.entetry.store.mapper;

import com.entetry.store.entity.Category;
import com.entetry.store.entity.Subcategory;
import com.entetry.storecommon.dto.CategoryDto;
import com.entetry.storecommon.dto.SubcategoryDto;
import org.springframework.stereotype.Component;

@Component
public class SubcategoryMapper {
    public SubcategoryDto toSubcategoryDto(Subcategory subcategory) {
        SubcategoryDto subcategoryDto = new SubcategoryDto();
        subcategoryDto.setId(subcategory.getId());
        subcategoryDto.setName(subcategory.getName());
        CategoryDto category = new CategoryDto();
        category.setName(subcategory.getCategory().getName());
        category.setId(subcategory.getCategory().getId());
        subcategoryDto.setCategory(category);
        return subcategoryDto;
    }

    public Subcategory toSubcategory(SubcategoryDto subcategoryDto) {
        Subcategory subcategory = new Subcategory();
        subcategory.setId(subcategoryDto.getId());
        Category category = new Category();
        category.setId(subcategoryDto.getCategory().getId());
        category.setName(subcategoryDto.getCategory().getName());
        subcategory.setCategory(category);
        return subcategory;
    }
}
