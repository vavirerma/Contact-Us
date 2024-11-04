package controller;

import dao.ContactRequestDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ContactRequest;

import java.io.IOException;
import java.sql.SQLException;


@WebServlet("/contactuss")
public class ContactServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
            response.sendRedirect("success.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("index.jsp");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

