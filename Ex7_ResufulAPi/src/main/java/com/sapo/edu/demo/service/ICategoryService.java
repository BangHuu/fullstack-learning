package com.sapo.edu.demo.service;

import com.sapo.edu.demo.model.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> findAll();
    String insertCategory(Category category) throws Exception;
    String updateCategory(Category category,String id) throws Exception;
    String deleteCategory(String id) throws Exception;

}
