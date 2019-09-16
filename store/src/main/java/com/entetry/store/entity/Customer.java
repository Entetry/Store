package com.entetry.store.entity;

import javax.persistence.*;
import java.util.*;

@Entity(name = "Customer")
@Table(name = "customer")
public class Customer extends AbstractEntity {
    @Id
    @GeneratedValue
    @Column(name = "customer_id")
    private Long id;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "customer_phone")
    private String phone;
    @Column(name = "customer_status")
    private String status;
    private String firstname;
    private String lastname;
    @Temporal(TemporalType.DATE)
    @Column(name = "dob")
    private Date dateOfBirth;
    private String sex;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Adress> adresses = new ArrayList<>();
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CreditCard> creditCards = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return getId().equals(customer.getId()) &&
                getUser().equals(customer.getUser()) &&
                Objects.equals(getPhone(), customer.getPhone()) &&
                getStatus().equals(customer.getStatus()) &&
                Objects.equals(getFirstname(), customer.getFirstname()) &&
                Objects.equals(getLastname(), customer.getLastname()) &&
                Objects.equals(getDateOfBirth(), customer.getDateOfBirth()) &&
                Objects.equals(getSex(), customer.getSex());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUser(), getPhone(), getFirstname(), getLastname());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public List<Adress> getAdresses() {
        return adresses;
    }

    public void setAdresses(List<Adress> adresses) {
        this.adresses = adresses;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<CreditCard> getCreditCards() {
        return creditCards;
    }

    public void setCreditCards(Set<CreditCard> creditCards) {
        this.creditCards = creditCards;
    }

    public void addAdress(Adress adress) {
        adresses.add(adress);
        adress.setCustomer(this);
    }

    public void removeAdress(Adress adress) {
        adresses.remove(adress);
        adress.setCustomer(null);
    }

    public void addCreditCard(CreditCard creditCard) {
        creditCards.add(creditCard);
        creditCard.setCustomer(this);
    }

    public void removeCreditCard(CreditCard creditCard) {
        creditCards.remove(creditCard);
        creditCard.setCustomer(null);
    }
}
