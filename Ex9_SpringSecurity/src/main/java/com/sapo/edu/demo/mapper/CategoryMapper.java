package com.sapo.edu.demo.mapper;

import com.sapo.edu.demo.model.Category;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class CategoryMapper implements RowMapper<Category> {
    @Override
    public Category mapRow(ResultSet resultSet, int i) throws SQLException {
        Category category = new Category();
        category.setId(resultSet.getInt("id"));
        category.setName(resultSet.getString("name"));
        category.setCreateDate(resultSet.getString("createDate"));
        category.setModifiedDate(resultSet.getString("modifiedDate"));
        category.setDescription(resultSet.getString("description"));
        return category;
    }
}
