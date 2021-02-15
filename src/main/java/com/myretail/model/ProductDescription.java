package com.myretail.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ProductDescription")
public class ProductDescription{
    @Id
    Long id;
    String name;

    public ProductDescription() {
            }

    public ProductDescription(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "ProductDescription{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
