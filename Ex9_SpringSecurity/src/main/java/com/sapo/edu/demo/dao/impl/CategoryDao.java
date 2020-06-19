package com.sapo.edu.demo.dao.impl;

import com.sapo.edu.demo.dao.ICategoryDao;
import com.sapo.edu.demo.dao.IProductDao;
import com.sapo.edu.demo.mapper.CategoryMapper;
import com.sapo.edu.demo.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowCountCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CategoryDao implements ICategoryDao {

  @Autowired private JdbcTemplate jdbcTemplate;
  @Autowired private IProductDao productDao;

  @Override
  /** todo Hiển thị List category và product thuộc category */
  public List<Category> findTotalCategory() {
    String sql = "select * from category";
    List<Category> categoryList = jdbcTemplate.query(sql, new CategoryMapper());
    for (Category item : categoryList) {
      item.setListProducts(productDao.searchByCategory(item.getId()));
    }
    return categoryList;
  }
  /**
   * todo Thêm category
   *
   * @param category
   * @return
   * @throws Exception
   */
  @Override
  public int insertCategory(Category category) throws Exception {

    return jdbcTemplate.update(
        "insert into category(name,createDate,modifiedDate,description) " + "VALUES (?,?,?,?)",
        new PreparedStatementSetter() {
          @Override
          public void setValues(PreparedStatement preparedStatement) throws SQLException {
            preparedStatement.setString(1, category.getName());
            preparedStatement.setString(2, category.getCreateDate());
            preparedStatement.setString(3, category.getModifiedDate());
            preparedStatement.setString(4, category.getDescription());
          }
        });
  }

  /**
   * todo Update category
   *
   * @param category
   * @return
   * @throws Exception
   */
  @Override
  public int updateCategory(Category category) throws Exception {
    checkInputCategory(category);
    if (getTotalCategoryById(category.getId()) == 0) {
      return insertCategory(category);
    } else {
      return jdbcTemplate.update(
          "Update category set name=? , createDate=?, modifiedDate=?,description=?  "
              + " where id=?",
          new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
              preparedStatement.setString(1, category.getName());
              preparedStatement.setString(2, category.getCreateDate());
              preparedStatement.setString(3, category.getModifiedDate());
              preparedStatement.setString(4, category.getDescription());
              preparedStatement.setInt(5, category.getId());
            }
          });
    }
  }
  /** todo Tìm 1 category (Kiểm tra có tồn tại ko */
  @Override
  public int getTotalCategoryById(int id) {
    RowCountCallbackHandler countCallback = new RowCountCallbackHandler(); // not reusable
    jdbcTemplate.query("select * from category where id=?", countCallback, id);
    return countCallback.getRowCount();
  }

  /**
   * todo Tìm các category và product theo id của category
   *
   * @param id
   * @return
   */
  @Override
  public Category getById(int id) {
    Category category =
        jdbcTemplate.queryForObject("select * from category where id=?", new CategoryMapper(), id);
    category.setListProducts(productDao.searchByCategory(id));
    return category;
  }

    /**
     * todo Xóa category theo id
     * @param id
     * @return
     */
    @Override
    public int deleteCategory(int id) {
        return jdbcTemplate.update("Delete from Category where id=?", new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setInt(1, id);
            }
        });
    }


    /** todo Check dữ liệu nhập vào */
  private void checkInputCategory(Category category) throws Exception {
    if (category.getCreateDate().compareTo(category.getModifiedDate()) < 0)
      throw new Exception("Create date cannot be late than Fix date");
  }
}
