package connections;

import java.sql.*;

public class DatabaseConnection {
    private static DatabaseConnection databaseConnection;
    private Connection connection;
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "ravi";

    private DatabaseConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static DatabaseConnection getInstance() {
        if (databaseConnection == null) {
            synchronized (DatabaseConnection.class) {
                if (databaseConnection == null) {
                    databaseConnection = new DatabaseConnection();
                }
            }
        }
        return databaseConnection;
    }

    public Connection getConnection() {
        return this.connection;
    }
}
