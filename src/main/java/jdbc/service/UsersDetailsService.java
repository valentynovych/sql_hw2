package jdbc.service;

import bdConnection.BDConnection;
import jdbc.dao.UsersDetailsDAO;
import jdbc.entity.UsersDetails;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersDetailsService extends BDConnection implements UsersDetailsDAO {
    Connection connection = getConnection();

    @Override
    public void addUsersDetails(UsersDetails usersDetails) {

        String sql = "INSERT INTO users_detrails VALUES (?, ?, ?, ?, ?)";
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(sql);
            statement.setLong(1, usersDetails.getDetailsId());
            statement.setString(2, usersDetails.getLastName());
            statement.setInt(3, usersDetails.getAge());
            statement.setString(4, usersDetails.getEmail());
            statement.setString(5, usersDetails.getCity());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatementAndConnection(statement, connection);
        }
    }

    @Override
    public UsersDetails getUsersDetailById(Long detailsId) {
        UsersDetails usersDetails = new UsersDetails();

        String sql = "SELECT last_name, age, email, city FROM details_id WHERE details_id = ?";

        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(sql);
            statement.setLong(1, detailsId);

            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            usersDetails.setLastName(resultSet.getString(1));
            usersDetails.setAge(resultSet.getInt(2));
            usersDetails.setEmail(resultSet.getString(3));
            usersDetails.setCity(resultSet.getString(4));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatementAndConnection(statement, connection);
        }

        return usersDetails;
    }

    @Override
    public List<UsersDetails> getAllUsersDetails() {
        List<UsersDetails> usersDetailsList = new ArrayList<>();

        String sql = "SELECT details_id, last_name, age, email, city FROM users_details";
        Statement statement = null;
        try {
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                UsersDetails usersDetails = new UsersDetails();
                usersDetails.setDetailsId(resultSet.getLong("details_id"));
                usersDetails.setLastName(resultSet.getString("last_name"));
                usersDetails.setAge(resultSet.getInt("age"));
                usersDetails.setEmail(resultSet.getString("email"));
                usersDetails.setCity(resultSet.getString("city"));
                usersDetailsList.add(usersDetails);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatementAndConnection(statement, connection);
        }
        return usersDetailsList;
    }

    @Override
    public void updateUsersDetailsById(UsersDetails usersDetails) {
        String sql = "UPDATE users_details SET last_name = ?, age = ?, email = ?, city = ? WHERE details_id = ?";
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(sql);

            statement.setString(1, usersDetails.getLastName());
            statement.setInt(2, usersDetails.getAge());
            statement.setString(3, usersDetails.getEmail());
            statement.setString(4, usersDetails.getCity());
            statement.setLong(5, usersDetails.getDetailsId());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatementAndConnection(statement, connection);
        }
    }

    @Override
    public void deleteUserDetailsById(Long detailsId) {
        String sql = "DELETE FROM users_detail WHERE details_id = ?";
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(sql);
            statement.setLong(1, detailsId);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatementAndConnection(statement, connection);
        }
    }
}
