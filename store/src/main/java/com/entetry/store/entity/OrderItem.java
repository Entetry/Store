package com.entetry.store.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "orders_item")
public class OrderItem {
    @EmbeddedId
    private OrderItemId id;
    @ManyToOne
    @JoinColumn(name="order_id",insertable = false,updatable = false)
    private Order order;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", insertable = false, updatable = false)
    private Item item;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "size_id", insertable = false, updatable = false)
    private Size size;
    @Column(name = "quantity")
    private int quantity;
    public OrderItem() {
    }

    public OrderItem(Order order, Item item, Size size,int quantity) {
        this.order = order;
        this.item = item;
        this.size = size;
        this.quantity=quantity;
        this.id = new OrderItemId(order.getId(), item.getId(), size.getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return getOrder().equals(orderItem.getOrder()) &&
                getItem().equals(orderItem.getItem()) &&
                getSize().equals(orderItem.getSize());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrder(), getItem(), getSize());
    }

    public OrderItemId getId() {
        return id;
    }

    public void setId(OrderItemId id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
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
