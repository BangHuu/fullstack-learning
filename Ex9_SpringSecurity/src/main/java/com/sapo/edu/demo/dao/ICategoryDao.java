package com.sapo.edu.demo.dao;

import com.sapo.edu.demo.model.Category;

import java.util.List;

public interface ICategoryDao {
    List<Category> findTotalCategory();

    int insertCategory(Category category) throws Exception;
    int updateCategory(Category category) throws Exception;
    int getTotalCategoryById(int id);
    Category getById(int id);
    int deleteCategory(int id);

}
