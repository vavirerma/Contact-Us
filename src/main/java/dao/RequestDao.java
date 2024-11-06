package dao;

import model.Request;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RequestDao {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "ravi";

    public void saveRequest(Request request) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        String sql = "INSERT INTO user_requests (full_name, email, message) VALUES (?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, request.getFullName());
            statement.setString(2, request.getEmail());
            statement.setString(3, request.getMessage());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Request> fetchRequests(boolean isArchived) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        String sql = "SELECT * FROM user_requests WHERE is_archived = ?";
        List<Request> requests = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setBoolean(1, isArchived);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Request request = new Request();
                request.setId(rs.getInt("id"));
                request.setFullName(rs.getString("full_name"));
                request.setEmail(rs.getString("email"));
                request.setMessage(rs.getString("message"));
                request.setCreatedAt(rs.getTimestamp("last_updated"));
                request.setArchived(rs.getBoolean("is_archived"));
                requests.add(request);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return requests;
    }

    public void changeStatus(int id, boolean isArchived) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        String sql = "UPDATE user_requests SET is_archived = ? WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setBoolean(1, !isArchived);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
