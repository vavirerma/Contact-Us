package servlets;

import dao.RequestDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Request;

import java.io.IOException;


@WebServlet("/")
public class ContactUsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("contactus.jsp");
        requestDispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fullName = request.getParameter("name");
        String email = request.getParameter("email");
        String message = request.getParameter("message");

        Request contactRequest = new Request();
        contactRequest.setFullName(fullName);
        contactRequest.setEmail(email);
        contactRequest.setMessage(message);

        RequestDao dao = new RequestDao();

        dao.saveRequest(contactRequest);
        response.sendRedirect(request.getContextPath() + "/");
    }
}

