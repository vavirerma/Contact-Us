package servlets;

import dao.UserDao;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;


import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/admin")
public class AdminLoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        UserDao userDAO = new UserDao();
        try {
            if (userDAO.validateUser(user)) {
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                session.setAttribute("password", password);
                response.sendRedirect("adminrequests");
            } else {
                response.getWriter().println("Invalid username or password");
            }
        } catch (SQLException e) {
            response.sendRedirect("login.jsp");
        } catch (ClassNotFoundException e) {
            response.getWriter().println(e.getMessage());
        }
    }
}

