package com.sapo.edu.demo.validate.exception;

public class CategoryNotFoundException extends RuntimeException  {
    public CategoryNotFoundException(int category_id){
        super("Could not found category_id "+category_id);
    }
}
