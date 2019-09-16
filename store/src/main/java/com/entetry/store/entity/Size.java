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
    @Column(name = "quantity")
    private int quantity;
    @ManyToMany(mappedBy = "sizes")
    private List<Item> items = new ArrayList<>();

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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
