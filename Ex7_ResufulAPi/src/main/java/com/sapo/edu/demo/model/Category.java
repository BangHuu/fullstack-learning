package com.sapo.edu.demo.model;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private int id;
    private String name;
    private String createDate;
    private String modifiedDate;
    private String description;
    private List<Product> listProducts ;
    public Category() {
    }

    public Category(int id, String name, String createDate, String modifiedDate, String description, List<Product> listProducts) {
        this.id = id;
        this.name = name;
        this.createDate = createDate;
        this.modifiedDate = modifiedDate;
        this.description = description;
        this.listProducts = listProducts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Product> getListProducts() {
        return listProducts;
    }

    public void setListProducts(List<Product> listProducts) {
        this.listProducts = listProducts;
    }
}
