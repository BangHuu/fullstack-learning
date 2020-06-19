package com.sapo.edu.demo.service;

import com.sapo.edu.demo.model.Product;

import java.util.List;

public interface IProductService {
    List<Product> searchByCategory(String id) throws Exception;
    List<Product> findAll();
    String insertProduct(Product product) throws Exception;
    String insertListProduct(List<Product> productList) throws Exception;
    String updateProduct(Product product,String id) throws Exception;
    String deleteProduct(String id) throws Exception;
}
