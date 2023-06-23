package jdbc;

import bdConnection.BDConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class Demo {
    public static void main(String[] args) {
        BDConnection myConnection = new BDConnection();
        Connection connection = myConnection.getConnection();
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
