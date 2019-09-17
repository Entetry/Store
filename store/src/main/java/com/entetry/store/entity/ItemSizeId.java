package com.entetry.store.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
@Embeddable
public class ItemSizeId implements Serializable {
    @Column(name = "item_id")
    private Long itemId;
    @Column(name = "size_id")
    private Long sizeId;
    public ItemSizeId(){}
    public ItemSizeId(Long itemId, Long sizeId){
        this.itemId=itemId;
        this.sizeId=sizeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemSizeId that = (ItemSizeId) o;
        return getItemId().equals(that.getItemId()) &&
                getSizeId().equals(that.getSizeId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getItemId(), getSizeId());
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getSizeId() {
        return sizeId;
    }

    public void setSizeId(Long sizeId) {
        this.sizeId = sizeId;
    }
}
