package com.entetry.store.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "item_size")
public class ItemSize {
    @EmbeddedId
    private ItemSizeId id;
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("item_id")
    private Item item;
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId( "size_id")
    private Size size;
    @Column(name="quantity")
    private int quantity;
    public ItemSize(Item item, Size size) {
        this.item = item;
        this.size = size;
        this.id= new ItemSizeId(item.getId(),size.getId());
    }

    public ItemSize() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemSize itemSize = (ItemSize) o;
        return getItem().equals(itemSize.getItem()) &&
                getSize().equals(itemSize.getSize());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getItem(), getSize());
    }

    public ItemSizeId getId() {
        return id;
    }

    public void setId(ItemSizeId id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
