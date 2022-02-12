package com.automation.pom.objects;

import com.automation.pom.utils.JacksonUtils;

public class Product {

    private int id;
    private String name;

    public Product() {
        // default empty constructor
    }

    public Product(int productId) {
        Product[] products = JacksonUtils.deserializeJsonToObject("Product.json", Product[].class);
        for (Product product : products) {
            if (product.getId() == productId) {
                this.name = product.getName();
            }
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
