package jdbc.service;

import bdConnection.BDConnection;
import jdbc.dao.ShoppingCardDAO;
import jdbc.entity.ShoppingCard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCardService extends BDConnection implements ShoppingCardDAO {
    Connection connection = getConnection();

    @Override
    public void addProductToCard(ShoppingCard shoppingCard) {
        String sql = "INSERT INTO shopping_cart VALUES (?, ?)";
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(sql);
            statement.setLong(1, shoppingCard.getCardId());
            statement.setLong(2, shoppingCard.getProduct_id());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatementAndConnection(statement, connection);
        }
    }

    @Override
    public void deleteProductById(Long cardId, Long productId) {
        String sql = "DELETE FROM shopping_cart WHERE card_id = ? AND product_id = ?";

        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setLong(1, cardId);
            statement.setLong(2, productId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatementAndConnection(statement, connection);
        }
    }

    @Override
    public List<Long> getAllProducts(Long cardId) {

        List<Long> listProductsIds = new ArrayList<>();
        String sql = "SELECT product_id from shopping_cart WHERE card_id = ?";

        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(sql);
            statement.setLong(1, cardId);

            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                Long productId = resultSet.getLong("product_id");
                listProductsIds.add(productId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatementAndConnection(statement, connection);
        }

        return listProductsIds;
    }

    @Override
    public void clearShoppingCardById(Long cardId) {
        String sql = "DELETE FROM shopping_cart WHERE card_id = ?";
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(sql);
            statement.setLong(1, cardId);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatementAndConnection(statement, connection);
        }
    }
}
