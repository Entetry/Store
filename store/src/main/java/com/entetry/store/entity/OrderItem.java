package com.entetry.store.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;
@Entity
@Table(name = "orders_item")
public class OrderItem
{
    @EmbeddedId
    private OrderItemId id;
    @ManyToOne
    @MapsId("order_id")
    private Order order;
    @ManyToOne
    @MapsId("item_id")
    private Item item;
    @ManyToOne
    @MapsId("size_id")
    private Size size;
    @Column(name = "offer_price")
    private BigDecimal offerPrice;
    @Column(name="quantity")
    private int quantity;
    private OrderItem(){}

    public OrderItem(Order order, Item item, Size size) {
        this.order = order;
        this.item = item;
        this.size = size;
        this.id=new OrderItemId(order.getId(),item.getId(),size.getId());
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

    public BigDecimal getOfferPrice() {
        return offerPrice;
    }

    public void setOfferPrice(BigDecimal offerPrice) {
        this.offerPrice = offerPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
