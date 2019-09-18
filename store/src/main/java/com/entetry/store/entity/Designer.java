package com.entetry.store.entity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "Designer")
@Table(name = "designer")
public class Designer extends AbstractEntity {
    @Id
    @GeneratedValue
    @Column(name = "designer_id")
    private Long id;
    @Column(name = "designer_name")
    private String designerName;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "designer_adress")
    private String designerAdress;
    @OneToMany(
            mappedBy = "designer",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<BankAccount> bankAccounts = new ArrayList<>();

    public void addBankAccount(BankAccount bankAccount) {
        bankAccounts.add(bankAccount);
        bankAccount.setDesigner(this);
    }

    public void removeBankAccount(BankAccount bankAccount) {
        bankAccounts.remove(bankAccount);
        bankAccount.setDesigner(null);
    }

    public List<BankAccount> getBankAccounts() {
        return bankAccounts;
    }

    public void setBankAccounts(List<BankAccount> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Designer designer = (Designer) o;
        return getId().equals(designer.getId()) &&
                getDesignerName().equals(designer.getDesignerName()) &&
                getUser().equals(designer.getUser()) &&
                Objects.equals(getDesignerAdress(), designer.getDesignerAdress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDesignerName(), getUser(), getDesignerAdress());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDesignerName() {
        return designerName;
    }

    public void setDesignerName(String designerName) {
        this.designerName = designerName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDesignerAdress() {
        return designerAdress;
    }

    public void setDesignerAdress(String designerAdress) {
        this.designerAdress = designerAdress;
    }
}
