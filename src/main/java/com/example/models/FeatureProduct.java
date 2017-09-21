package com.example.models;

import io.leangen.graphql.annotations.GraphQLQuery;

import javax.persistence.*;

@Entity
@Table(name="feature_product")
public class FeatureProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    @GraphQLQuery(name="id")
    private long id;

    @Column(name="img")
    @GraphQLQuery(name="img")
    private String img;

    @Column(name="title")
    @GraphQLQuery(name="title")
    private String title;

    @Column(name="content")
    @GraphQLQuery(name="content")
    private String content;

    @Column(name="price")
    @GraphQLQuery(name="price")
    private double price;

    public FeatureProduct() {}

    public FeatureProduct(long id, String img, String title, String content, double price) {
        this.id = id;
        this.img = img;
        this.title = title;
        this.content = content;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
