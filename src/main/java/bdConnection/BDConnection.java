package bdConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BDConnection {
    private static final String url = "jdbc:mysql://localhost:3306/new_shop";
    private static final String username = "root";
    private static final String password = "root";

    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Connection fail");
        }
        return connection;
    }
    public void closeStatementAndConnection(Statement statement, Connection connection) {
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