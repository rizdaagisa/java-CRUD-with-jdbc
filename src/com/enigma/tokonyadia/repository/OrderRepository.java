package com.enigma.tokonyadia.repository;

import com.enigma.tokonyadia.DTO.OrderRequest;
import com.enigma.tokonyadia.DTO.OrderResponse;

import java.sql.SQLException;
import java.util.List;

public interface OrderRepository {
    void save(OrderRequest request) throws SQLException;
    OrderResponse getById(Integer id) throws SQLException;
    List<OrderResponse> findAll() throws SQLException;
}
