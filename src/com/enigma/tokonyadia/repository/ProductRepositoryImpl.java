package com.enigma.tokonyadia.repository;

import com.enigma.tokonyadia.config.DbConnector;
import com.enigma.tokonyadia.entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryImpl implements ProductRepository {
    Connection connection;

    @Override
    public void deleteById(Integer id) throws SQLException {
        this.connection = DbConnector.connect();
        String query = "DELETE FROM m_product WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        int resultCount = statement.executeUpdate();
        if (resultCount <= 0) {
            statement.close();
            throw new RuntimeException("ERROR DELETE data");
        } else {
            System.out.println("Success DELETE data : " + resultCount);
        }
    }

    @Override
    public Product save(Product product) throws SQLException {
        this.connection = DbConnector.connect();
        String query = "INSERT into m_product (name,description,price,stock,store_id) VALUES(?,?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, product.getName());
        statement.setString(2, product.getDescription());
        statement.setLong(3, product.getPrice());
        statement.setInt(4, product.getStock());
        statement.setInt(5, product.getStoreId());
        int resultCount = statement.executeUpdate();

        ResultSet generatedKeys = statement.getGeneratedKeys();
        if (generatedKeys.next()){
            product.setId(generatedKeys.getInt(1));
            System.out.println("Success SAVE data : " + resultCount);
        }
        generatedKeys.close();
        statement.close();
        connection.close();
        return product;
    }

    @Override
    public List<Product> findAll() throws SQLException {
        this.connection = DbConnector.connect();
        String query = "SELECT * FROM m_product";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet rs = statement.executeQuery();

        List<Product> products = new ArrayList<>();
        while (rs.next()) {
            products.add(new Product(
                    rs.getInt("id"),
                    rs.getInt("store_id"),
                    rs.getString("description"),
                    rs.getLong("price"),
                    rs.getInt("stock"),
                    rs.getString("name")
            ));
        }
        statement.close();
        connection.close();
        return products;
    }

    @Override
    public Product findById(Integer id) {
        Product product = new Product();
        this.connection = DbConnector.connect();
        String query = "SELECT * FROM m_product WHERE id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setStock(rs.getInt("stock"));
                product.setPrice(rs.getLong("price"));
                product.setStoreId(rs.getInt("store_id"));
                statement.close();
                connection.close();
                return product;
            }
        } catch (Exception e) {
            System.out.println("DATA NOT FOUND " + e);
        }
        return product;
    }

    @Override
    public Product update(Product product) throws SQLException {
        this.connection = DbConnector.connect();
        String query = "UPDATE m_product SET  name=?, description=?, price=?, stock=?, store_id=? WHERE id=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, product.getName());
        statement.setString(2, product.getDescription());
        statement.setLong(3, product.getPrice());
        statement.setInt(4, product.getStock());
        statement.setInt(5, product.getStoreId());
        statement.setInt(6, product.getId());
        int resultCount = statement.executeUpdate();
        if (resultCount <= 0) {
            statement.close();
            throw new RuntimeException("ERROR UPDATE data");
        } else {
            System.out.println("Success UPDATE data : " + resultCount);
        }
        statement.close();
        connection.close();
        return product;
    }
}
