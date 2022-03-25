package com.selenium.pom.objects;

import com.selenium.pom.utils.JacksonUtils;

public class Product {
    private int id;
    private String name;

    public Product(){}

    public Product(int id) {
        Product[] products = new Product[0];
        products = JacksonUtils.deserializeJsonFileToJsonObject("products", Product[].class);
        for(Product product: products){
            if(product.getId() == id){
                this.id = id;
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
