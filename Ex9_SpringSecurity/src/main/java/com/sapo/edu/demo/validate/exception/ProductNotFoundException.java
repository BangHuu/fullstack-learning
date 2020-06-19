package com.sapo.edu.demo.validate.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(int id){super("Could not found id "+ id);}
}
