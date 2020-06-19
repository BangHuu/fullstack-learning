package com.sapo.edu.demo.service.impl;

import com.sapo.edu.demo.dao.ICategoryDao;
import com.sapo.edu.demo.dao.IProductDao;
import com.sapo.edu.demo.model.Category;
import com.sapo.edu.demo.model.Product;
import com.sapo.edu.demo.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class CategoryService implements ICategoryService {
  @Autowired
  private IProductDao productDao;
    @Autowired
    private ICategoryDao categoryDao;
    @Override
    public List<Category> findAll() {

        return categoryDao.findTotalCategory();
    }

    @Override
    public String insertCategory(Category category) throws Exception {
        String result ="Add Category failed";
        if(category!=null){
            result = "Add Category successfully ";
            result += "\nYou have add "+categoryDao.insertCategory(category)+" category";
        }
        return result;
    }

    @Override
    public String updateCategory(Category category, String id) throws Exception {

        String result = "";
        try {
            category.setId(Integer.parseInt(id));
            Category old = categoryDao.getById(Integer.parseInt(id));
            result = "Edit Product failed";
            int row = categoryDao.updateCategory(category);
            if (row > 0)
                result = "\nYou have Update " + row + " Category on id= " + id +
                        "\n" + "Old Category:" + old.toString() +
                        "\nNew Category:" + category.toString();
        } catch (NumberFormatException ignored) {
            return "You can't input text into id to search";
        }
        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String deleteCategory(String id) throws Exception {
        String result = "";
        try {
            int id_raw = Integer.parseInt(id);
            Category old = categoryDao.getById(id_raw);
            List<Product> productList = productDao.searchByCategory(id_raw);
            int product_row = 0;
            int category_row = 0;
            if (productDao.searchByCategory(id_raw).size() > 0) {
                product_row = productDao.deleteProductByCategory(id_raw);
            }
            category_row = categoryDao.deleteCategory(id_raw);
            if (category_row == 0) {
                throw new Exception("There are no Category with id : " + id);
            }
            int deleted_row = product_row + category_row;
            if (deleted_row > 0) {
                result = "\nYou have Delete " + deleted_row + " with Category id= " + id +
                        "\n" + "Deleted Category:" + old.toString() +
                        "\nDeleted Product:";
                for (Product i : productList) {
                    result += "\n" + i.toString();
                }
            }

        } catch (NumberFormatException ignored) {
            throw new Exception("You can't input text into id to delete, must be number");
        }

        return result;
    }
}
