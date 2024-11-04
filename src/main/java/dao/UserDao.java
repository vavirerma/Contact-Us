package dao;


import model.User;

import java.sql.*;

public class UserDao {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "ravi";

    public boolean validateUser(User user) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1, user.getUsername());
        statement.setString(2, user.getPassword());
        ResultSet rs = statement.executeQuery();
        return rs.next();
    }
}
