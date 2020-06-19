package com.sapo.edu.demo.service.impl;

import com.sapo.edu.demo.dao.ICategoryDao;
import com.sapo.edu.demo.dao.IProductDao;
import com.sapo.edu.demo.model.Product;
import com.sapo.edu.demo.service.IProductService;
import com.sapo.edu.demo.validate.exception.CategoryNotFoundException;
import com.sapo.edu.demo.validate.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class ProductService implements IProductService {
    @Autowired
    IProductDao productDao;
    @Autowired
    ICategoryDao categoryDao;
    @Override
    public List<Product> searchByCategory(String id) throws Exception {
        List<Product> products =null;
        try{
            int id_raw=Integer.parseInt(id);
            try{
                products = productDao.searchByCategory(id_raw);
            }catch (DataAccessException ex){
                throw new CategoryNotFoundException(id_raw);
            }
        }catch (NumberFormatException ex){
            throw new Exception("Please input right category id format");
        }
        return products;
    }

    @Override
    public List<Product> findAll() {
    return productDao.findAllProduct();
    }

    @Override
    public String insertProduct(Product product) throws Exception {
        String result = "Add Product failed";
        if (product != null)
            result = "Add Product successfully ";
        result += "\nYou have add " + productDao.insertProduct(product) + " Product";
        return result;
    }

    @Override
    public String insertListProduct(List<Product> productList) throws Exception {
        String result = "";
        if (productList.isEmpty())
            return "Add Product failed";
        if (!productList.isEmpty())
            result = "Add Product successfully ";
        for (Product i : productList
        ) {
            checkInputProduct(i);
        }
        result += "\nYou have add " + productDao.insertListProduct(productList) + " Product";
        return result;
    }

    @Override
    public String updateProduct(Product product,String id) throws Exception {
        String result = "";

        try {
            product.setId(Integer.parseInt(id));
            checkInputProduct(product);
            Product old = productDao.getById(Integer.parseInt(id));

            result += "\nYou have Update " + productDao.updateProduct(product) + " Product on id= " + id +
                    "\n" + product.toString()
                    + "\n Old Product: " + old.toString()
                    + "\n New Product: " + product.toString();
        } catch (NumberFormatException ex) {
            return "You can't input text into id to search";
        }
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String deleteProduct(String id) throws Exception {
        String result = "";
        try {
            int id_raw = Integer.parseInt(id);
            try {
                int row = productDao.deleteProductById(id_raw);
                result = "You have deleted " + row + " row(s) from Product with id: " + id_raw;
            } catch (DataAccessException ex) {
                throw new ProductNotFoundException(id_raw);
            }

        } catch (NumberFormatException ex) {
            throw new Exception("Please input right id format");
        }
        return result;
    }

    /**
     * Check dữ liệu nhập vào
     * @param product
     * @throws Exception
     */
    private void checkInputProduct(Product product) throws Exception {

        if (product.getQuantity() < 0) {
            throw new Exception("Product " + product.getId() + ": Quantity can't be less than 0");
        }

        if (product.getCategory_id() < 0 || categoryDao.getTotalCategoryById(product.getCategory_id()) == 0) {
            throw new Exception("Product "+product.getId()+":Must input a valid category ");
        }

    }
}
