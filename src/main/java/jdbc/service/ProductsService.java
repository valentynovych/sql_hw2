package jdbc.service;

import bdConnection.BDConnection;
import jdbc.dao.ProductsDAO;
import jdbc.entity.Products;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductsService extends BDConnection implements ProductsDAO {

    Connection connection;

    @Override
    public void addProducts(Products product) {
        connection = getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO products (product_id, product_name, product_price, amount) " +
                "VALUES (?, ?, ?, ?)";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, product.getProductId());
            preparedStatement.setString(2, product.getProductName());
            if (product.getProductPrice() == null){
                preparedStatement.setNull(3, 1);
            } else {
                preparedStatement.setDouble(3, product.getProductPrice());
            }
            if (product.getAmount() == null){
                preparedStatement.setNull(4, 1);
            } else {
                preparedStatement.setInt(4, product.getAmount());
            }


            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            closeStatementAndConnection(preparedStatement, connection);
        }
    }

    @Override
    public Products getProductById(Long productId) {
        connection = getConnection();
        Products product = null;
        String sql = "SELECT product_id, product_name, product_price, amount FROM products WHERE product_id = ?";
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(sql);
            statement.setLong(1, productId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                product = new Products();
                product.setProductId(resultSet.getLong(1));
                product.setProductName(resultSet.getString(2));
                product.setProductPrice(resultSet.getDouble(3));
                product.setAmount(resultSet.getInt(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatementAndConnection(statement, connection);
        }
        return product;
    }

    @Override
    public List<Products> getAllProducts() {
        connection = getConnection();
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
        connection = getConnection();
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
        connection = getConnection();

        String sql = "DELETE FROM products WHERE product_id = ?";
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
}
