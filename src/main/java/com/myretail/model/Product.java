package com.myretail.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Product {
    private Long id;
    private String name;

    @JsonProperty("current_price")
    private ProductPrice price;

    public Product(Long id, String name, ProductPrice price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ProductPrice getPrice() {
        return price;
    }
}
