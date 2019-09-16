package com.entetry.store.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "Category")
@Table(name = "category")
public class Category extends AbstractEntity {
    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private Long id;
    @Column(name = "category_name")
    private String name;
    @ElementCollection(targetClass=Subcategory.class)
    private List<Subcategory> subcategories = new ArrayList<>();

    public List<Subcategory> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(List<Subcategory> subcategories) {
        this.subcategories = subcategories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return getId().equals(category.getId()) &&
                getName().equals(category.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
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
}
