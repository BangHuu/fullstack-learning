package com.sapo.edu.demo.input;

import com.sapo.edu.demo.model.Product;

import java.math.BigDecimal;
import java.util.Scanner;

public class Input {
    Scanner scanner = new Scanner(System.in);

    //TODO tao 1 product
    public Product createProduct() {
        System.out.println("Name :");
        String name = scanner.nextLine();
        System.out.println("Image : ");
        String image = scanner.nextLine();
        System.out.println("Price : ");
        BigDecimal price = scanner.nextBigDecimal();
        System.out.println("Quantity :  ");
        int quantity = scanner.nextInt();
        System.out.println("Description :");
        String description = scanner.nextLine();
        System.out.println("Category_id :");
        int category_id = scanner.nextInt();
        Product product = new Product(name, image, price, quantity,description,category_id);
        return product;
    }
}
