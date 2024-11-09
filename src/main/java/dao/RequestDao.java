package dao;

import connections.DatabaseConnection;
import model.Request;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RequestDao {

    Connection connection;

    public RequestDao() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    public void saveRequest(Request request) {
        String query = "INSERT INTO user_requests (full_name, email, message) VALUES (?, ?, ?)";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, request.getFullName());
            statement.setString(2, request.getEmail());
            statement.setString(3, request.getMessage());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Request> fetchRequests(boolean isArchived) {
        String query = "SELECT * FROM user_requests WHERE is_archived = ?";
        List<Request> requests = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement(query);
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
        String query = "UPDATE user_requests SET is_archived = ? WHERE id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setBoolean(1, !isArchived);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}