package com.entetry.store.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.*;

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
    @ElementCollection(targetClass=Image.class)
    private List<Image> images = new ArrayList<>();
    @OneToMany(mappedBy = "item",cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<ItemSize> itemSizes = new ArrayList<>();
    public void addSize(Size size) {
        ItemSize itemSize = new ItemSize(this,size);
        itemSizes.add(itemSize);
        size.getItemSizes().add(itemSize);
    }

    public void removeSize(Size size) {
        for (Iterator<ItemSize> iterator = itemSizes.iterator();
             iterator.hasNext(); ) {
            ItemSize itemSize = iterator.next();

            if (itemSize.getItem().equals(this) &&
                    itemSize.getSize().equals(size)) {
                iterator.remove();
                itemSize.getSize().getItemSizes().remove(size);
                itemSize.setItem(null);
                itemSize.setSize(null);
            }
        }
    }
    public List<ItemSize> getItemSizes() {
        return itemSizes;
    }

    public void setItemSizes(List<ItemSize> itemSizes) {
        this.itemSizes = itemSizes;
    }

    public List<Image> getImages() {
        return images;
    }
    public void setImages(List<Image> images) {
        this.images = images;
    }
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
