package com.enigma.tokonyadia.repository;

import com.enigma.tokonyadia.entity.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductRepository {

    void deleteById(Integer id) throws SQLException;
    Product save(Product product) throws SQLException;
    List<Product> findAll() throws SQLException;
    Product findById(Integer id);
    Product update(Product product) throws SQLException;

}
