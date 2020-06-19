package com.sapo.edu.demo.dao;

import com.sapo.edu.demo.model.Product;

import java.util.List;

public interface IProductDao {
    List<Product> searchByCategory(int category_id);
    List<Product> findAllProduct();
    int insertProduct(Product product) throws Exception;
    int insertListProduct(List<Product> productList) throws Exception;
    int updateProduct(Product product) throws Exception;
    Product getById(int id);
    int deleteProductById(int id);
    int deleteProductByCategory(int id) throws Exception;
}
