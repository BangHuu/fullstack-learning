package com.sapo.edu.demo;

import com.sapo.edu.demo.dao.ProductDao;
import com.sapo.edu.demo.input.Input;
import com.sapo.edu.demo.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class Main implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Autowired
    ProductDao productDao;

    Scanner scanner = new Scanner(System.in);
    Input input = new Input();

    @Override
    public void run(String... args) throws Exception {

        int value = 0;

        do {
            showMenu();
            System.out.println("Mời bạn nhập lựa chọn");
            value = scanner.nextInt();
            switch (value) {
                case 1:
                    insertProduct();
                    break;
                case 2:
                    updateProduct();
                    break;
                case 3:
                    deleteProduct();

                    break;
                case 4:
                    findAll();
                    break;
                case 5:
                    System.exit(0);
            }
        } while (value > 5 || value < 0);
    }

    public void showMenu() {
        System.out.println("1 : Insert");
        System.out.println("2 : Update");
        System.out.println("3 : Delete");
        System.out.println("4 : GetAll");
        System.out.println("5 : Exit");
    }

    // todo Kiểm tra id có tồn tại trong bảng
    public boolean checkIsxisId(int id) {
        List<Product> productList = productDao.findAll();
        int a = 0;
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getId() == id) {
                a = 1;
            }
        }
        if (a == 1) return true;
        else return false;
    }

    public void findAll() {
        productDao.findAll();
    }

    public void insertProduct() {
        System.out.println("----INSERT------");
        Product product = input.createProduct();
        productDao.insert(product);
    }

    public void updateProduct() {
        System.out.println("-----UPDATE-------");
        int id = 0;
        do {
            System.out.println("Input ID to Update!");
            if (checkIsxisId(id)) {
                break;
            }
        } while (true);
        Product product = input.createProduct();
        productDao.update(id, product);
    }

    public void deleteProduct() {
        System.out.println("-------DELETE--------");
        int id = 0;
        do {
            System.out.println("Input ID to Delete!");
            if (checkIsxisId(id)) {
                break;
            }
        } while (true);
        productDao.delete(id);
    }


}
