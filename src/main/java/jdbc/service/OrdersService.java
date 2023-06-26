package jdbc.service;

import bdConnection.BDConnection;
import jdbc.dao.OrdersDAO;
import jdbc.entity.Orders;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdersService extends BDConnection implements OrdersDAO {
    Connection connection = getConnection();
    @Override
    public void saveOrder(Orders orders) {
        String sql = "INSERT INTO orders VALUES (?, ?, ?, ?, ?)";
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(sql);
            statement.setLong(1, orders.getOrderId());
            statement.setLong(2, orders.getUserId());
            statement.setString(3, orders.getListProducts());
            statement.setDate(4, orders.getOrderDate());
            statement.setDouble(5, orders.getOrderPrice());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatementAndConnection(statement, connection);
        }

    }

    @Override
    public List<Orders> getAllUserOrdersById(Long userId) {
        List<Orders> userOdersList = new ArrayList<>();
        String sql = "SELECT user_id, list_products, order_date, order_price FROM orders";

        Statement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next()){
                Orders order = new Orders();
                order.setOrderId(userId);
                order.setUserId(resultSet.getLong(1));
                order.setListProducts(resultSet.getString(2));
                order.setOrderDate(resultSet.getDate(3));
                order.setOrderPrice(resultSet.getDouble(4));
                userOdersList.add(order);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatementAndConnection(statement, connection);
        }
        return userOdersList;
    }

    @Override
    public List<Orders> getAllOrders() {
        List<Orders> ordersList = new ArrayList<>();
        String sql = "SELECT order_id, user_id, list_products, order_date, order_price FROM orders";

        Statement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next()){
                Orders order = new Orders();
                order.setOrderId(resultSet.getLong(1));
                order.setUserId(resultSet.getLong(2));
                order.setListProducts(resultSet.getString(3));
                order.setOrderDate(resultSet.getDate(4));
                order.setOrderPrice(resultSet.getDouble(5));
                ordersList.add(order);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatementAndConnection(statement, connection);
        }
        return ordersList;
    }
}
