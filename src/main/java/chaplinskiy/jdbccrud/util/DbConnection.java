package chaplinskiy.jdbccrud.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static chaplinskiy.jdbccrud.util.Constants.*;

public class DbConnection {
    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName(JDBC_DRIVER);
                connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            } catch (ClassNotFoundException e) {
                System.out.println("Unable to load class.");
                e.printStackTrace();
            } catch (SQLException e) {
                System.out.println("Cant create connection");
                e.printStackTrace();
            }
        }
        return connection;
    }
    public static void closeConnection() throws SQLException {
        try {
            connection.close();
        } catch (Exception e){

        } finally {
            connection.close();
        }

    }

}
