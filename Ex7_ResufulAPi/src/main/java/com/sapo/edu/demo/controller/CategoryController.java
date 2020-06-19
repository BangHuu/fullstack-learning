package com.sapo.edu.demo.controller;

import com.sapo.edu.demo.model.Category;
import com.sapo.edu.demo.service.impl.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/category")
public class CategoryController {

  @Autowired private CategoryService categoryService;

  @GetMapping("/findAll")
  public List<Category> fillCategory() {
    return categoryService.findAll();
  }

  @PostMapping("/save")
  public String insertCategory(@RequestBody Category category) throws Exception {
    return categoryService.insertCategory(category);
  }

  @PutMapping("/update/{id}")
  public String updateCategory(@RequestBody Category category, @PathVariable("id") String id)
      throws Exception {
    return categoryService.updateCategory(category, id);
  }
}
