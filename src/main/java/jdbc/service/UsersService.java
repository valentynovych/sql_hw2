package jdbc.service;

import bdConnection.BDConnection;
import jdbc.dao.UsersDAO;
import jdbc.entity.Users;
import jdbc.entity.UsersDetails;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersService extends BDConnection implements UsersDAO {

    Connection connection;

    @Override
    public void addUser(Users users) {
        connection = getConnection();
        String sql = "INSERT INTO users " +
                "VALUES (?, ?, ?, ?, ?)";
        PreparedStatement statement = null;

        try {
            UsersDetails usersDetails = new UsersDetails();
            usersDetails.setDetailsId(users.getDetailsId());
            UsersDetailsService detailsService = new UsersDetailsService();
            detailsService.addUsersDetails(usersDetails);

            statement = connection.prepareStatement(sql);
            statement.setLong(1, users.getUserId());
            statement.setString(2, users.getFirstName());
            statement.setInt(3, users.getOrdersCount());
            statement.setLong(4, users.getDetailsId());
            statement.setLong(5, users.getCardId());
            statement.executeUpdate();



        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatementAndConnection(statement, connection);
        }
    }

    @Override
    public Users getUserById(Long userId) {
        connection = getConnection();
        Users user = null;
        String sql = "SELECT user_id, first_name, orders_count, details_id, card_id " +
                "FROM users " +
                "WHERE user_id = ?";
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(sql);
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                user = new Users();
                user.setUserId(resultSet.getLong("user_id"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setOrdersCount(resultSet.getInt("orders_count"));
                user.setDetailsId(resultSet.getLong("details_id"));
                user.setCardId(resultSet.getLong("card_id"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatementAndConnection(statement, connection);
        }
        return user;
    }

    @Override
    public List<Users> getAllUsers() {
        connection = getConnection();
        List<Users> usersList = new ArrayList<>();

        String sql = "SELECT user_id, first_name, orders_count, details_id, card_id " +
                "FROM users";

        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Users user = new Users();
                user.setUserId(resultSet.getLong("user_id"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setOrdersCount(resultSet.getInt("orders_count"));
                user.setDetailsId(resultSet.getLong("details_id"));
                user.setCardId(resultSet.getLong("card_id"));
                usersList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatementAndConnection(statement, connection);
        }
        return usersList;
    }

    @Override
    public void updateUserById(Users users) {
        connection = getConnection();
        String sql = "UPDATE users " +
                "SET first_name = ?, orders_count = ?, details_id = ?, card_id = ? " +
                "WHERE user_id = ?";
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, users.getFirstName());
            statement.setInt(2, users.getOrdersCount());
            statement.setLong(3, users.getDetailsId());
            statement.setLong(4, users.getCardId());
            statement.setLong(5, users.getUserId());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatementAndConnection(statement, connection);
        }
    }

    @Override
    public void deleteUserById(Long userId) {

        connection = getConnection();
        String sql = "DELETE FROM users WHERE user_id = ?";
        PreparedStatement statement = null;
        String sql2 = "DELETE FROM users_details WHERE details_id = ?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setLong(1, userId);
            statement.executeUpdate();

            statement = connection.prepareStatement(sql2);
            statement.setLong(1, userId);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatementAndConnection(statement, connection);
        }
    }
}
