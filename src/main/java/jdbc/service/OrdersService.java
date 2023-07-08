package jdbc.service;

import bdConnection.BDConnection;
import jdbc.dao.OrdersDAO;
import jdbc.entity.Orders;
import jdbc.entity.Products;
import jdbc.entity.Users;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrdersService extends BDConnection implements OrdersDAO {
    Connection connection;

    public Orders createOrder(Users user) {
        ShoppingCardService cardService = new ShoppingCardService();
        ProductsService productsService = new ProductsService();

        List<Long> listIdProducts = cardService.getAllProducts(user.getCardId());
        List<Products> products = new ArrayList<>();

        for (Long productId : listIdProducts) {
            Products product = productsService.getProductById(productId);
            products.add(product);
        }
        StringBuilder listProductsOfString = new StringBuilder();
        Double orderPrice = 0.0;

        for (int i = 0; i < products.size(); i++) {
            Products product = products.get(i);
            String productName = product.getProductName();
            orderPrice += product.getProductPrice();
            listProductsOfString.append(productName);
            if (i < products.size() - 1) {
                listProductsOfString.append(", ");
            }
        }

        Orders newOrder = new Orders();
        newOrder.setOrderId(getMaxId() + 1);
        newOrder.setUserId(user.getUserId());
        newOrder.setOrderPrice(orderPrice);
        newOrder.setOrderDate(Date.valueOf(LocalDate.now()));
        newOrder.setListProducts(listProductsOfString.toString());

        saveOrder(newOrder);
        UsersService usersService = new UsersService();
        user.setOrdersCount(user.getOrdersCount() + 1);
        usersService.updateUserById(user);
        cardService.clearShoppingCardById(user.getCardId());
        return newOrder;
    }

    @Override
    public void saveOrder(Orders orders) {
        connection = getConnection();

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
        connection = getConnection();
        List<Orders> userOdersList = new ArrayList<>();
        String sql = "SELECT order_id, user_id, list_products, order_date, order_price FROM orders WHERE user_id = ?";

        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setLong(1, userId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Orders order = new Orders();
                order.setOrderId(resultSet.getLong(1));
                order.setUserId(resultSet.getLong(2));
                order.setListProducts(resultSet.getString(3));
                order.setOrderDate(resultSet.getDate(4));
                order.setOrderPrice(resultSet.getDouble(5));
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
        connection = getConnection();
        List<Orders> ordersList = new ArrayList<>();
        String sql = "SELECT order_id, user_id, list_products, order_date, order_price FROM orders";

        Statement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
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

    protected Long getMaxId() {
        connection = getConnection();
        Long maxId = null;
        String sql = "SELECT max(order_id) from orders";
        Statement statement;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            maxId = resultSet.getLong(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maxId;
    }
}
