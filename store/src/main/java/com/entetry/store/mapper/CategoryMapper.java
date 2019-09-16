package com.entetry.store.mapper;

import com.entetry.store.entity.Category;
import com.entetry.storecommon.dto.CategoryDto;
import com.entetry.storecommon.dto.SubcategoryDto;

import java.util.stream.Collectors;

public class CategoryMapper {
    public static CategoryDto toCategoryDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        categoryDto.setSubcategories(category.getSubcategories().stream().map(SubcategoryMapper::toSubcategoryDto).collect(Collectors.toList()));
        return categoryDto;
    }

    public static Category toCategory(CategoryDto categoryDto) {
        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setName(categoryDto.getName());
        category.setSubcategories(categoryDto.getSubcategories().stream().map(SubcategoryMapper::toSubcategory).collect(Collectors.toList()));
        return category;
    }
}
