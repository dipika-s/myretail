package com.myretail.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ProductPrice")
public class ProductPrice {
    @Id
    @JsonIgnore
    private Long id;
    private Double value;
    @JsonProperty("currency_code")
    private String currencyCode;

    public ProductPrice(){

    }

    public ProductPrice(Long id, Double value, String currencyCode) {
        this.id = id;
        this.value = value;
        this.currencyCode = currencyCode;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Double getValue() {
        return value;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    @Override
    public String toString() {
        return "ProductPrice{" +
                "id=" + id +
                ", value=" + value +
                ", currencyCode='" + currencyCode + '\'' +
                '}';
    }
}
