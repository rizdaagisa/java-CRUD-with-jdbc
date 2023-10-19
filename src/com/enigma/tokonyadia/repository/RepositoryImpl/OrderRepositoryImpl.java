package com.enigma.tokonyadia.repository.RepositoryImpl;

import com.enigma.tokonyadia.DTO.OrderDetailRequest;
import com.enigma.tokonyadia.DTO.OrderDetailResponse;
import com.enigma.tokonyadia.DTO.OrderRequest;
import com.enigma.tokonyadia.DTO.OrderResponse;
import com.enigma.tokonyadia.config.DbConnector;
import com.enigma.tokonyadia.repository.OrderRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderRepositoryImpl implements OrderRepository {

    @Override
    public void save(OrderRequest request) throws SQLException {
        try (Connection connection = DbConnector.connect()) {
            connection.setAutoCommit(false);

            int orderid = 0;
            PreparedStatement orderStatement = connection.prepareStatement(
                    "INSERT INTO t_transaction (customer_id, transaction_date) VALUES(?,?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            orderStatement.setInt(1, request.getCustomerId());
            orderStatement.setDate(2, new Date(System.currentTimeMillis()));
            orderStatement.executeUpdate();

            ResultSet generatedKeys = orderStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                System.out.println("SUCCESS INSERT DATA t_transaction");
                orderid = generatedKeys.getInt(1);
            } else {
                connection.rollback();
            }
            orderStatement.close();

            PreparedStatement orderDetailStatement = connection.prepareStatement("INSERT INTO t_transaction_detail (transaction_id, product_id, quantity, price) VALUES (?,?,?,?)");
            for (OrderDetailRequest orderDetail : request.getOrderDetailRequests()) {
                orderDetailStatement.clearParameters();
                orderDetailStatement.setInt(1, orderid);
                orderDetailStatement.setInt(2, orderDetail.getProductId());
                orderDetailStatement.setInt(3, orderDetail.getQuantity());
                orderDetailStatement.setLong(4, orderDetail.getPrice());
                orderDetailStatement.addBatch();
            }

            orderDetailStatement.executeBatch();
            orderDetailStatement.close();

            connection.commit();
        }
    }

    @Override
    public OrderResponse getById(Integer id) throws SQLException {
        try (Connection connection = DbConnector.connect()) {

            String query = "select \n" +
                    "\ttt.id as transaction_id ,\n" +
                    "\ttt.transaction_date ,\n" +
                    "\tmc.\"name\" customer_name,\n" +
                    "\tms.\"name\" store_name ,\n" +
                    "\tmp.\"name\" product_name,\n" +
                    "\tmp.price ,\n" +
                    "\tttd.quantity ,\n" +
                    "\tmp.price * ttd.quantity as subTotal\n" +
                    "from t_transaction_detail ttd \n" +
                    "join m_product mp on mp.id = ttd.id\n" +
                    "join t_transaction tt on tt.id = ttd.transaction_id\n" +
                    "join m_customer mc on mc.id = tt.id\n" +
                    "join m_store ms on ms.id = mp.store_id\n" +
                    "WHERE ttd.transaction_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            Map<Integer, OrderResponse> mapData = new HashMap<>();

            while (resultSet.next()) {
                Integer transaction_id = resultSet.getInt("transaction_id");
                Date trans_date = resultSet.getDate("transaction_date");
                String customer_name = resultSet.getString("customer_name");
                String store_name = resultSet.getString("store_name");
                String product_name = resultSet.getString("product_name");
                Long price = resultSet.getLong("price");
                Integer quantitiy = resultSet.getInt("quantity");
                Long subtotal = resultSet.getLong("subTotal");

                OrderResponse orderResponse = mapData.get(transaction_id);
                if (orderResponse == null) {
                    orderResponse = new OrderResponse(
                            transaction_id,
                            trans_date,
                            customer_name
                    );
                    mapData.put(transaction_id, orderResponse);
                }
                OrderDetailResponse orderDetailResponse = new OrderDetailResponse(store_name, product_name, price, quantitiy, subtotal);
                orderResponse.setOrderDetails(orderDetailResponse); // memasukan data barang kedalam list
            }

            statement.close();
            resultSet.close();
            return mapData.get(id);
        } catch (SQLException e) {
            throw new SQLException();
        }
    }

    @Override
    public List<OrderResponse> findAll() throws SQLException {
        try (Connection connection = DbConnector.connect()) {
            String query = "select \n" +
                    "\ttt.id as transaction_id ,\n" +
                    "\ttt.transaction_date ,\n" +
                    "\tmc.\"name\" customer_name,\n" +
                    "\tms.\"name\" store_name ,\n" +
                    "\tmp.\"name\" product_name,\n" +
                    "\tmp.price ,\n" +
                    "\tttd.quantity ,\n" +
                    "\tmp.price * ttd.quantity as subTotal\n" +
                    "from t_transaction_detail ttd\n" +
                    "join t_transaction tt on tt.id = ttd.transaction_id \n" +
                    "join m_product mp on mp.id = ttd.product_id\n" +
                    "join m_store ms ON ms.id = mp.store_id\n" +
                    "join m_customer mc on mc.id = tt.customer_id;";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            Map<Integer, OrderResponse> mapData = getOrderMap(resultSet);

            statement.close();
            resultSet.close();
            return new ArrayList<>(mapData.values());
        } catch (SQLException e) {
            throw new SQLException();
        }
    }

    private Map getOrderMap(ResultSet resultSet) {
        try{
            Map<Integer, OrderResponse> mapData = new HashMap<>();
            while (resultSet.next()) {
                Integer transaction_id = resultSet.getInt("transaction_id");
                Date trans_date = resultSet.getDate("transaction_date");
                String customer_name = resultSet.getString("customer_name");
                String store_name = resultSet.getString("store_name");
                String product_name = resultSet.getString("product_name");
                Long price = resultSet.getLong("price");
                Integer quantitiy = resultSet.getInt("quantity");
                Long subtotal = resultSet.getLong("subTotal");

                OrderResponse orderResponse = mapData.get(transaction_id);
                if (orderResponse == null) {
                    orderResponse = new OrderResponse(
                            transaction_id,
                            trans_date,
                            customer_name
                    );
                    mapData.put(transaction_id, orderResponse);
                }
                OrderDetailResponse orderDetailResponse = new OrderDetailResponse(store_name, product_name, price, quantitiy, subtotal);
                orderResponse.setOrderDetails(orderDetailResponse); // memasukan data barang kedalam list
            }
            return mapData;
        } catch (RuntimeException e){
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
