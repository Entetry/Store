package com.entetry.store.mapper;

import com.entetry.store.entity.Category;
import com.entetry.storecommon.dto.CategoryDto;
import com.entetry.storecommon.dto.SubcategoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CategoryMapper {
    private final SubcategoryMapper subcategoryMapper;

    public CategoryMapper(SubcategoryMapper subcategoryMapper) {
        this.subcategoryMapper = subcategoryMapper;
    }

    public CategoryDto toCategoryDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        categoryDto.setSubcategories(category.getSubcategories().stream().map(subcategory -> subcategoryMapper.toSubcategoryDto(subcategory)).collect(Collectors.toList()));
        return categoryDto;
    }

    public Category toCategory(CategoryDto categoryDto) {
        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setName(categoryDto.getName());
        category.setSubcategories(categoryDto.getSubcategories().stream().map(subcategory -> subcategoryMapper.toSubcategory(subcategory)).collect(Collectors.toList()));
        return category;
    }
}
