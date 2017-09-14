package com.example.models;

import io.leangen.graphql.annotations.GraphQLQuery;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;

@Entity
@Table(name="new_product")
@Proxy(lazy = false)
public class NewProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    @GraphQLQuery(name="id")
    private long id;

    @Column(name="img")
    @GraphQLQuery(name="img")
    private String img;

    @Column(name="title")
    @GraphQLQuery(name="title")
    private String title;

    @Column(name="description")
    @GraphQLQuery(name="description")
    private String description;

    @Column(name="discount")
    @GraphQLQuery(name="discount")
    private int discount;

    @Column(name="original_price")
    @GraphQLQuery(name="original_price")
    private double originalPrice;

    @Column(name="price")
    @GraphQLQuery(name="price")
    private double price;

    public NewProduct(){}

    public NewProduct(long id, String img, String title,
                      String description, int discount,
                      double originalPrice, double price) {
        this.id = id;
        this.img = img;
        this.title = title;
        this.description = description;
        this.discount = discount;
        this.originalPrice = originalPrice;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
