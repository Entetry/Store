package com.entetry.store.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OrderItemId implements Serializable {
    @Column(name = "order_id")
    private Long orderId;
    @Column(name = "item_id")
    private Long itemId;
    @Column(name = "size_id")
    private Long sizeId;
    private OrderItemId(){}
    public OrderItemId(Long orderId, Long itemId, Long sizeId) {
        this.orderId = orderId;
        this.itemId = itemId;
        this.sizeId = sizeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItemId that = (OrderItemId) o;
        return getOrderId().equals(that.getOrderId()) &&
                getItemId().equals(that.getItemId()) &&
                getSizeId().equals(that.getSizeId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrderId(), getItemId(), getSizeId());
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
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
