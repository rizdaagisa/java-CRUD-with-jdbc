package com.enigma.tokonyadia.repository;

import com.enigma.tokonyadia.entity.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CustomerRepository {

    Customer update(Customer customer) throws SQLException;

    Customer save(Customer customer) throws SQLException;

    List<Customer> findAll() throws SQLException;

    void deleteById(Integer id) throws SQLException;

    Customer findById(Integer id) throws SQLException;
}
