package jdbc.service;

import bdConnection.BDConnection;
import jdbc.dao.ProductsDAO;
import jdbc.entity.Products;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductsService extends BDConnection implements ProductsDAO {

    Connection connection = getConnection();

    @Override
    public void addProducts(Products product) {
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO products (product_id, product_name, product_price, amount) " +
                "VALUES (?, ?, ?, ?)";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, product.getProductId());
            preparedStatement.setString(2, product.getProductName());
            preparedStatement.setDouble(3, product.getProductPrice());
            preparedStatement.setInt(4, product.getAmount());

            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            closeStatementAndConnection(preparedStatement, connection);
        }
    }

    @Override
    public Products getProductById(Long productId) {
        Products product = new Products();
        String sql = "SELECT product_id, product_name, product_price, amount FROM products WHERE product_id = ?";
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(sql);
            statement.setLong(1, productId);

            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            product.setProductId(resultSet.getLong("product_id"));
            product.setProductName(resultSet.getString("product_name"));
            product.setProductPrice(resultSet.getDouble("product_price"));
            product.setAmount(resultSet.getInt("amount"));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatementAndConnection(statement, connection);
        }
        return product;
    }

    @Override
    public List<Products> getAllProducts() {
        List<Products> productsList = new ArrayList<>();

        String sql = "SELECT product_id, product_name, product_price, amount FROM products";
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Products product = new Products();
                product.setProductId(resultSet.getLong("product_id"));
                product.setProductName(resultSet.getString("product_name"));
                product.setProductPrice(resultSet.getDouble("product_price"));
                product.setAmount(resultSet.getInt("amount"));
                productsList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatementAndConnection(statement, connection);
        }
        return productsList;
    }

    @Override
    public void updateProductById(Long productId, Products product) {
        String sql = "UPDATE products SET product_name = ?, product_price = ?, amount = ? WHERE product_id = ?";
        PreparedStatement statement = null;

        try {

            statement = connection.prepareStatement(sql);

            statement.setString(1, product.getProductName());
            statement.setDouble(2, product.getProductPrice());
            statement.setInt(3, product.getAmount());
            statement.setLong(4, productId);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatementAndConnection(statement, connection);
        }
    }

    @Override
    public void deleteProductById(Long productId) {

        String sql = "DELETE FROM products where product_id = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setLong(1, productId);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatementAndConnection(statement, connection);
        }
    }

    private void closeStatementAndConnection(Statement statement, Connection connection) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
