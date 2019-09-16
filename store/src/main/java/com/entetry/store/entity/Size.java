package com.entetry.store.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Size")
@Table(name = "size")
public class Size {
    @Id
    @Column(name = "size_id")
    @GeneratedValue
    private Long id;
    @Column(name = "size")
    private String size;
    @OneToMany(mappedBy = "size")
    private List<ItemSize> itemSizes = new ArrayList<>();
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
