package com.celfocus.training.services;

import com.celfocus.training.models.Product;
import com.celfocus.training.models.ShoppingCart;
import com.celfocus.training.models.User;

import java.util.ArrayList;
import java.util.List;

public class ProductService {

    private UserService userService;
    private static final List<Product> products = new ArrayList<>();

    public static List<Product> getProducts() {
        return products;
    };

    public Product find(String name) {
        for (Product product : products) {
            if (product.getName().equals(name)) {
                return product;
            }
        }
        return null;
    };



}
