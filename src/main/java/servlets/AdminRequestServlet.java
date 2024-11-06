package servlets;

import dao.ContactRequestDao;
import dao.UserDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.ContactRequest;
import model.User;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/adminrequests")
public class AdminRequestServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (isUserAuthenticated(request, response)) {
            ContactRequestDao dao = new ContactRequestDao();
            try {
                List<ContactRequest> activeRequests = dao.getRequests(false);
                List<ContactRequest> archivedRequests = dao.getRequests(true);

                request.setAttribute("activeRequests", activeRequests);
                request.setAttribute("archivedRequests", archivedRequests);
                request.getRequestDispatcher("requests.jsp").forward(request, response);
            } catch (SQLException e) {
                response.sendRedirect("error.jsp");
            } catch (ClassNotFoundException e) {
                response.sendRedirect("error.jsp");
            }
        } else {
            response.sendRedirect("login.jsp");
        }
    }

    public boolean isUserAuthenticated(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        String password = (String) session.getAttribute("password");

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        UserDao userDao = new UserDao();
        try {
            if (userDao.validateUser(user)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}

