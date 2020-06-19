package com.sapo.edu.demo.controller;

import com.sapo.edu.demo.model.Product;
import com.sapo.edu.demo.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/product")
public class ProductController {
    @Autowired
    private IProductService productService;
    @GetMapping("/findAll")
    public List<Product> findAll(){
        return productService.findAll();
    }
    @PostMapping("/save")
    public String insertProduct(@RequestBody Product product) throws Exception{
        return productService.insertProduct(product);
    }
    @PostMapping("/save/list")
    public String insertListProduct(@RequestBody List<Product> products) throws Exception{
        return productService.insertListProduct(products);
    }
    @PutMapping("update/{id}")
    public String updateProduct(@RequestBody Product product,@PathVariable("id") String id) throws Exception {
        return productService.updateProduct(product,id);
    }
    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") String id) throws Exception{
        return productService.deleteProduct(id);
    }
}
