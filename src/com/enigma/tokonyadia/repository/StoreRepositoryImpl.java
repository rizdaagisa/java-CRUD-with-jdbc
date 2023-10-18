package com.enigma.tokonyadia.repository;

import com.enigma.tokonyadia.config.DbConnector;
import com.enigma.tokonyadia.entity.Store;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StoreRepositoryImpl implements StoreRepository {
    private Connection connection;
    public StoreRepositoryImpl(){}

    @Override
    public List<Store> findAll() throws SQLException {
        this.connection = DbConnector.connect();
        String query = "SELECT * FROM m_store";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet rs = statement.executeQuery();

        List<Store> stores = new ArrayList<>();
        while (rs.next()) {
            stores.add(new Store(
                    rs.getString("name"),
                    rs.getString("phone_number"),
                    rs.getInt("id"),
                    rs.getString("siup_number"),
                    rs.getString("address")
            ));
        }
        statement.close();
        connection.close();
        return stores;
    }

    @Override
    public void deleteById(Integer id) throws SQLException {
        this.connection = DbConnector.connect();
        String query = "DELETE FROM m_store WHERE id = ?";
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
    public Store findById(Integer id) {
        Store store = new Store();
        this.connection = DbConnector.connect();
        String query = "SELECT * FROM m_store WHERE id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                store.setSiupNumber(rs.getString("siup_number"));
                store.setAddress(rs.getString("address"));
                store.setId(rs.getInt("id"));
                store.setName(rs.getString("name"));
                store.setPhoneNumber(rs.getString("phone_number"));
                statement.close();
                connection.close();
                return store;
            }
        } catch (Exception e) {
            System.out.println("DATA NOT FOUND " + e);
        }
        return store;
    }

    @Override
    public void update(Store store) throws SQLException {
        this.connection = DbConnector.connect();
        String query = "UPDATE m_store SET name=?, siup_number=?, address=?, phone_number=? WHERE id=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, store.getName());
        statement.setString(2, store.getSiupNumber());
        statement.setString(3, store.getAddress());
        statement.setString(4, store.getPhoneNumber());
        statement.setInt(5, store.getId());
        int resultCount = statement.executeUpdate();
        if (resultCount <= 0) {
            statement.close();
            throw new RuntimeException("ERROR UPDATE data");
        } else {
            System.out.println("Success UPDATE data : " + resultCount);
        }
        statement.close();
        connection.close();
    }

    @Override
    public void save(Store store) throws SQLException {
        this.connection = DbConnector.connect();
        String query = "INSERT into m_store (id,siup_number,name,address,phone_number) VALUES(?,?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, store.getId());
        statement.setString(2, store.getSiupNumber());
        statement.setString(3, store.getName());
        statement.setString(4, store.getAddress());
        statement.setString(5, store.getPhoneNumber());

        int resultCount = statement.executeUpdate();
        statement.close();
        if (resultCount <= 0) {
            statement.close();
            throw new RuntimeException("ERROR SAVE data");
        } else {
            System.out.println("Success SAVE data : " + resultCount);
        }
        statement.close();
    }
}
