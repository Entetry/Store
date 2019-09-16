package com.entetry.store.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity(name = "Item")
@Table(name = "item")
public class Item extends AbstractEntity {
    @Id
    @Column(name = "item_id")
    @GeneratedValue
    private Long id;
    @Column(name = "item_name")
    private String name;
    @Temporal(TemporalType.DATE)
    @Column(name = "publish_date")
    private Date publishDate;
    @Column(name = "item_status")
    private String itemStatus;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_designer_id")
    private Designer designer;
    @Column(name = "item_price")
    private BigDecimal price;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subcategory_id")
    private Subcategory subcategory;
    @Column(name = "sex")
    private String sex;
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "item_size", joinColumns = @JoinColumn(name = "item_id"), inverseJoinColumns = @JoinColumn(name = "size_id"))
    private List<Size> sizes = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return getId().equals(item.getId()) &&
                getName().equals(item.getName()) &&
                getDesigner().equals(item.getDesigner()) &&
                getSubcategory().equals(item.getSubcategory()) &&
                getSex().equals(item.getSex());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDesigner(), getSubcategory(), getSex());
    }

    public void addSize(Size size) {
        sizes.add(size);
        size.getItems().add(this);
    }

    public void removeSize(Size size) {
        sizes.add(size);
        size.getItems().remove(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public String getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(String itemStatus) {
        this.itemStatus = itemStatus;
    }

    public Designer getDesigner() {
        return designer;
    }

    public void setDesigner(Designer designer) {
        this.designer = designer;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Subcategory getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(Subcategory subcategory) {
        this.subcategory = subcategory;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
