package com.entetry.store.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "Adress")
@Table(name = "adress")
public class Adress extends AbstractEntity {
    @Id
    @GeneratedValue
    @Column(name = "adress_id")
    private Long id;
    private String firstname;
    private String lastname;
    private String adress;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @Column(name = "post_index")
    private String postIndex;
    private String city;
    private String region;
    @Column(name = "phone_number")
    private String phone;
    private String email;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Adress adress1 = (Adress) o;
        return getId().equals(adress1.getId()) &&
                getFirstname().equals(adress1.getFirstname()) &&
                getLastname().equals(adress1.getLastname()) &&
                getAdress().equals(adress1.getAdress()) &&
                getCustomer().equals(adress1.getCustomer()) &&
                getPostIndex().equals(adress1.getPostIndex()) &&
                getCity().equals(adress1.getCity()) &&
                getRegion().equals(adress1.getRegion()) &&
                getPhone().equals(adress1.getPhone()) &&
                getEmail().equals(adress1.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstname(), getLastname(), getAdress(), getCustomer(), getPostIndex(), getCity(), getRegion(), getPhone(), getEmail());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getPostIndex() {
        return postIndex;
    }

    public void setPostIndex(String postIndex) {
        this.postIndex = postIndex;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
