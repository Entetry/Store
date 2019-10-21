package com.entetry.store.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.*;

@Entity(name = "Order")
@Table(name = "orders")
public class Order extends AbstractEntity {
    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;
    @Column(name = "order_date")
    private Date orderDate;
    @Column(name = "order_cost")
    private BigDecimal cost;
    @Column(name = "order_status")
    private String orderStatus;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "designer_id")
    private Designer designer;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return getId().equals(order.getId()) &&
                getOrderDate().equals(order.getOrderDate()) &&
                getCost().equals(order.getCost()) &&
                getDesigner().equals(order.getDesigner()) &&
                getCustomer().equals(order.getCustomer());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getOrderDate(), getCost(), getDesigner(), getCustomer());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Designer getDesigner() {
        return designer;
    }

    public void setDesigner(Designer designer) {
        this.designer = designer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public void addItem(Item item, Size size, int quantity) {
        OrderItem orderItem = new OrderItem(this, item, size, quantity);
        items.add(orderItem);

    }

    public void removeItem(Item item, Size size) {
        for (Iterator<OrderItem> iterator = items.iterator(); iterator.hasNext(); ) {
            OrderItem orderItem = iterator.next();
            if (orderItem.getOrder().equals(this) && orderItem.getItem().equals(item) && orderItem.getSize().equals(size)) {
                iterator.remove();
                orderItem.setOrder(null);
                orderItem.setItem(null);
                orderItem.setSize(null);
            }

        }
    }
}
