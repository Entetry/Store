package com.entetry.store.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "Size")
@Table(name = "size")
public class Size {
    @Id
    @Column(name = "size_id")
    @GeneratedValue
    private Long id;
    @Column(name = "size_name")
    private String size;
    @OneToMany(mappedBy = "size", cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<ItemSize> itemSizes = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Size size1 = (Size) o;
        return getSize().equals(size1.getSize());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSize());
    }

    public List<ItemSize> getItemSizes() {
        return itemSizes;
    }

    public void setItemSizes(List<ItemSize> itemSizes) {
        this.itemSizes = itemSizes;
    }

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

}
