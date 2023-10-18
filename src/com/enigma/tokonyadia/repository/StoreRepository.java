package com.enigma.tokonyadia.repository;

import com.enigma.tokonyadia.entity.Store;

import java.sql.SQLException;
import java.util.List;

public interface StoreRepository {

    List<Store> findAll() throws SQLException;
    void deleteById(Integer id) throws SQLException;
    Store findById(Integer id);
    void update(Store store) throws SQLException;
    void save(Store store) throws SQLException;

}
