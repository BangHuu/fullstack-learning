package com.sapo.edu.demo.dao;

import com.sapo.edu.demo.mapper.ProductMapper;
import com.sapo.edu.demo.model.Product;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductDao {
    ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
    JdbcTemplate jdbcTemplate = (JdbcTemplate) ctx.getBean("jdbcTemplate");
    // todo tạo bảng product
    public void createTable(){
        String sql = "create table product(" +
                "id int primary key auto_increment," +
                "    name nvarchar(20) not null," +
                "    image nvarchar(255) ," +
                "    price decimal(18,4)," +
                "    quantity int," +
                "    description nvarchar(255)," +
                "    category_id int" +
                ")";
        jdbcTemplate.execute(sql);
        System.out.println("Create table !!!");
    }
    // todo Lấy dữ liệu trong bảng
    public List<Product> findAll(){
        String sql = "select * from product";
        List<Product> productList = jdbcTemplate.query(sql,new ProductMapper());
        for(Product product:productList){
            System.out.println(product.getId() + " "+product.getName()+" "+product.getImage()
            +" "+product.getPrice()+" "+product.getQuantity()+" "+ product.getQuantity()+ " "+product.getCategory_id());
        }
        return productList;
    }
    // todo Thêm 1 product mới
    public void insert(Product product) {
        String sql = "INSERT INTO product (name, image, price, quantity,description,category_id)"
                + " VALUES (?, ?, ?, ?,?,?)";

        jdbcTemplate.update(sql, product.getName(), product.getImage(), product.getPrice(),
                product.getQuantity(), product.getDescription(), product.getCategory_id());
    }
    // todo Cập nhập danh sách product
    public void update(int id, Product product) {
        String sql = "UPDATE product SET name=?, image=?, price=?, "
                + "quantity=?,description=? ,category_id = ? WHERE id=?";

        jdbcTemplate.update(sql, product.getName(),product.getImage(),
                product.getPrice(), product.getQuantity(), product.getDescription(), product.getCategory_id(), id);
    }
    // todo xóa product theo id
    public void delete(int id) {
        String sql = "DELETE FROM Product1 WHERE ID=?";
        jdbcTemplate.update(sql, id);
    }

}
