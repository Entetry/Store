package com.entetry.store.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "Image")
@Table(name = "image")
public class Image extends AbstractEntity {
    @Id
    @GeneratedValue
    @Column(name = "image_id")
    private Long id;
    @Column(name = "image_name")
    private String name;
    @Column(name = "image_url")
    private String url;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        return Objects.equals(getId(), image.getId()) &&
                getName().equals(image.getName()) &&
                getUrl().equals(image.getUrl()) &&
                getItem().equals(image.getItem());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getUrl(), getItem());
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
