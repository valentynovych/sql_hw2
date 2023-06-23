package bdConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BDConnection {
    private static final String url = "jdbc:mysql://localhost:3306/new_shop";
    private static final String username = "root";
    private static final String password = "root";

    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connection OK");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Connection fail");
        }
        return connection;
    }
}