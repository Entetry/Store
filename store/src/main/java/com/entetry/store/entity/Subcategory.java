package com.entetry.store.entity;

import javax.persistence.*;

@Entity(name = "Subcategory")
@Table(name = "subcategory")
public class Subcategory extends AbstractEntity {
    @Id
    @Column(name = "role_id")
    @GeneratedValue
    private Long id;
    @Column(name = "category_name")
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
