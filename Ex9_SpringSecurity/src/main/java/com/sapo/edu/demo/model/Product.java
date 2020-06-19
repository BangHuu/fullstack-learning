package com.sapo.edu.demo.model;

import java.math.BigDecimal;

public class Product {
    private Integer id;

    private String name;

    private BigDecimal price;

    private String img_link;

    private Integer quantity;

    private String description;

    private Integer category_id;

    public Product() {
    }

    public Product(Integer id, String name, BigDecimal price, String img_link, Integer quantity, Integer sale_number, String description, Integer category_id) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.img_link = img_link;
        this.quantity = quantity;
        this.description = description;
        this.category_id = category_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImg_link() {
        return img_link;
    }

    public void setImg_link(String img_link) {
        this.img_link = img_link;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", img_link='" + img_link + '\'' +
                ", quantity=" + quantity +
                ", description='" + description + '\'' +
                ", category_id=" + category_id +
                '}';
    }
}
