package dao;

import connections.DatabaseConnection;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    Connection connection;

    public UserDao() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    public boolean isValidUser(User user) {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            ResultSet rs = statement.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}