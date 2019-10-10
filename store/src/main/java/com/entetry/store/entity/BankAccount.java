
package com.entetry.store.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity(name = "BankAccount")
@Table(name = "bank_account")
public class BankAccount extends AbstractEntity {
    @Id
    @GeneratedValue
    @Column(name = "bank_account_id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "designer_id")
    private Designer designer;
    @Column(name = "balance")
    private BigDecimal balance;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankAccount that = (BankAccount) o;
        return getId().equals(that.getId()) &&
                getDesigner().equals(that.getDesigner()) &&
                getBalance().equals(that.getBalance());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDesigner(), getBalance());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Designer getDesigner() {
        return designer;
    }

    public void setDesigner(Designer designer) {
        this.designer = designer;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
