package com.enigma.tokonyadia.repository.RepositoryImpl;

import com.enigma.tokonyadia.config.DbConnector;
import com.enigma.tokonyadia.entity.Customer;
import com.enigma.tokonyadia.repository.CustomerRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepositoryImpl implements CustomerRepository {

    private Connection connection;
    public CustomerRepositoryImpl() {}

    @Override
    public Customer save(Customer customer) throws SQLException {
        this.connection = DbConnector.connect();
        String query = "INSERT into m_customer (name,address,phone_number,email) VALUES(?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, customer.getName());
        statement.setString(2, customer.getPhoneNumber());
        statement.setString(3, customer.getAddress());
        statement.setString(4, customer.getEmail());
        int resultCount = statement.executeUpdate();
        statement.close();
        if (resultCount <= 0) {
            statement.close();
            throw new RuntimeException("ERROR SAVE data");
        } else {
            System.out.println("Success SAVE data : " + resultCount);
        }
        statement.close();
        connection.close();
        return new Customer(
                customer.getEmail(),
                customer.getAddress(),
                customer.getId(),
                customer.getEmail(),
                customer.getPhoneNumber()
        );
    }

    @Override
    public Customer findById(Integer id) {
        Customer customer = new Customer();
        this.connection = DbConnector.connect();
        String query = "SELECT * FROM m_customer WHERE id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                customer.setEmail(rs.getString("email"));
                customer.setAddress(rs.getString("address"));
                customer.setId(rs.getInt("id"));
                customer.setName(rs.getString("name"));
                customer.setPhoneNumber(rs.getString("phone_number"));
                statement.close();
                connection.close();
                return customer;
            }
        } catch (Exception e) {
            System.out.println("DATA NOT FOUND " + e);
        }
        return customer;
    }

    @Override
    public List<Customer> findAll() throws SQLException {
        this.connection = DbConnector.connect();
        String query = "SELECT * FROM m_customer";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet rs = statement.executeQuery();

        List<Customer> customers = new ArrayList<>();
        while (rs.next()) {
            customers.add(new Customer(
                    rs.getString("email"),
                    rs.getString("address"),
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("phone_number")
            ));
        }
        statement.close();
        connection.close();
        return customers;
    }

    @Override
    public Customer update(Customer customer) throws SQLException {
        this.connection = DbConnector.connect();
        String query = "UPDATE m_customer SET name=?, email=?, address=?, phone_number=? WHERE id=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, customer.getName());
        statement.setString(2, customer.getEmail());
        statement.setString(3, customer.getAddress());
        statement.setString(4, customer.getPhoneNumber());
        statement.setInt(5, customer.getId());
        int resultCount = statement.executeUpdate();
        if (resultCount <= 0) {
            statement.close();
            throw new RuntimeException("ERROR UPDATE data");
        } else {
            System.out.println("Success UPDATE data : " + resultCount);
        }
        statement.close();
        connection.close();
        return new Customer(
                customer.getEmail(),
                customer.getAddress(),
                customer.getId(),
                customer.getEmail(),
                customer.getPhoneNumber()
        );
    }

    @Override
    public void deleteById(Integer id) throws SQLException {
        this.connection = DbConnector.connect();
        String query = "DELETE FROM m_customer WHERE id = ?";
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
}
