package com.sapo.edu.demo.dao.impl;

import com.sapo.edu.demo.dao.IProductDao;
import com.sapo.edu.demo.mapper.ProductMapper;
import com.sapo.edu.demo.model.Product;
import com.sapo.edu.demo.validate.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
@Repository
public class ProductDao implements IProductDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     *Tìm kiếm các sản phẩm thuộc category
     */
    @Override
    public List<Product> searchByCategory(int category_id) {
        String sql = "select * from product p where p.category_id=? ";
        return jdbcTemplate.query(sql,new ProductMapper(),category_id);
    }

    /**
     * hiển thị list product
     * @return
     */
    @Override
    public List<Product> findAllProduct() {
        String sql = "select * from product";
        return jdbcTemplate.query(sql,new ProductMapper());
    }

    /**
     * Thêm product
     * @param product
     * @return
     * @throws Exception
     */
    @Override
    public int insertProduct(Product product) throws Exception {
        return jdbcTemplate.update("insert into Product(name,img_link,price,quantity,description,category_id) " +
                "VALUES (?,?,?,?,?,?)", new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, product.getName());
                preparedStatement.setString(2, product.getImg_link());
                preparedStatement.setBigDecimal(3, product.getPrice());
                preparedStatement.setInt(4, product.getQuantity());
                preparedStatement.setString(5, product.getDescription());
                preparedStatement.setInt(6, product.getCategory_id());
            }
        });
    }

    /**
     * Thêm nhiều sản phẩm cùng lúc
     * @param productList
     * @return
     * @throws Exception
     */
    @Override
    public int insertListProduct(List<Product> productList) throws Exception {
        for (Product item:productList){
            insertProduct(item);
        }
        return productList.size();
    }

    @Override
    public int updateProduct(Product product) throws Exception {
        if (getById(product.getId()) == null)
            return insertProduct(product);
        else
            return jdbcTemplate.update("Update product set name=?,img_link=?,price=?,quantity=?,description=?,category_id=?" +
                    " where id=?", new PreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement preparedStatement) throws SQLException {
                    preparedStatement.setString(1, product.getName());
                    preparedStatement.setString(2, product.getImg_link());
                    preparedStatement.setBigDecimal(3, product.getPrice());
                    preparedStatement.setInt(4, product.getQuantity());
                    preparedStatement.setString(5, product.getDescription());
                    preparedStatement.setInt(6, product.getCategory_id());
                    preparedStatement.setInt(7, product.getId());
                }
            });
    }

    /**
     * Tìm sản phẩm theo id
     * @param id
     * @return
     */
    @Override
    public Product getById(int id) {
        Product product;
        try {
            product = jdbcTemplate.queryForObject("select * from product p where p.id=?", new ProductMapper(), id);
        } catch (DataAccessException e) {
            throw new ProductNotFoundException(id);
        }
        return product;
    }

    @Override
    public int deleteProductById(int id) {
        String sql = "delete from product where id=?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public int deleteProductByCategory(int id) throws Exception {
        return jdbcTemplate.update("Delete from Product where category_id=?", id);
    }


}
