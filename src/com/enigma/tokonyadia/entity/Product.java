package com.enigma.tokonyadia.entity;

import java.util.Objects;

public class Product {

    Integer storeId;
    Integer id;
    String description;
    Long price;
    Integer stock;
    String name;

    public Product(){}

    public Product(Integer id, Integer storeId, String description, Long price, Integer stock, String name) {
        this.id = id;
        this.storeId = storeId;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(storeId, product.storeId) && Objects.equals(description, product.description) && Objects.equals(price, product.price) && Objects.equals(stock, product.stock) && Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, storeId, description, price, stock, name);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", storeId=" + storeId +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", name='" + name + '\'' +
                '}';
    }
}
