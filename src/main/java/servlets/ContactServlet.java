package servlets;

import dao.ContactRequestDao;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ContactRequest;

import java.io.IOException;
import java.sql.SQLException;


@WebServlet("/contactus")
public class ContactServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String fullName = request.getParameter("name");
        String email = request.getParameter("email");
        String message = request.getParameter("message");

        ContactRequest contactRequest = new ContactRequest();
        contactRequest.setFullName(fullName);
        contactRequest.setEmail(email);
        contactRequest.setMessage(message);

        ContactRequestDao dao = new ContactRequestDao();
        try {
            dao.saveRequest(contactRequest);
            response.sendRedirect("recordsaved.jsp");
        } catch (SQLException e) {
            response.sendRedirect("index.jsp");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

