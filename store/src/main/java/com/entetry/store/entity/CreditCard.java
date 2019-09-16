package com.entetry.store.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity(name = "CreditCard")
@Table(name = "credit_card")
public class CreditCard extends AbstractEntity {
    @Id
    @GeneratedValue
    @Column(name = "creditcard_id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @Column(name = "balance")
    private BigDecimal balance;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditCard that = (CreditCard) o;
        return getId().equals(that.getId()) &&
                getCustomer().equals(that.getCustomer()) &&
                getBalance().equals(that.getBalance());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCustomer(), getBalance());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
