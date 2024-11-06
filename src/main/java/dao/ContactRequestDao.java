package dao;

import lombok.Getter;
import lombok.Setter;
import model.ContactRequest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactRequestDao {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "ravi";

    public void saveRequest(ContactRequest request) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        String sql = "INSERT INTO user_requests (full_name, email, message) VALUES (?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, request.getFullName());
            statement.setString(2, request.getEmail());
            statement.setString(3, request.getMessage());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<ContactRequest> getRequests(boolean archived) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        String sql = "SELECT * FROM user_requests WHERE is_archived = ?";
        List<ContactRequest> requests = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setBoolean(1, archived);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                ContactRequest request = new ContactRequest();
                request.setId(rs.getInt("id"));
                request.setFullName(rs.getString("full_name"));
                request.setEmail(rs.getString("email"));
                request.setMessage(rs.getString("message"));
                request.setArchived(rs.getBoolean("is_archived"));
                requests.add(request);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requests;
    }

    public void archiveRequest(int id) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        String sql = "UPDATE user_requests SET is_archived = TRUE WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void activeRequest(int id) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        String sql = "UPDATE user_requests SET is_archived = FALSE WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
