package servlets;

import dao.ContactRequestDao;
import dao.UserDao;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/changerequesttype")
public class SetRequestToArchiveOrActiveServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (isUserAuthenticated(request, response)) {
            String checkRequestType = request.getParameter("action");
            if (checkRequestType.equals("archive")) {
                int requestId = Integer.parseInt(request.getParameter("requestId"));
                ContactRequestDao dao = new ContactRequestDao();

                try {
                    dao.archiveRequest(requestId);
                    response.sendRedirect("adminrequests");
                } catch (SQLException e) {
                    response.getWriter().println(e.getMessage());
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            } else {
                int requestId = Integer.parseInt(request.getParameter("requestId"));
                ContactRequestDao dao = new ContactRequestDao();

                try {
                    dao.activeRequest(requestId);
                    response.sendRedirect("adminrequests");
                } catch (SQLException e) {
                    response.sendRedirect("error.jsp");
                } catch (ClassNotFoundException e) {
                    response.sendRedirect("error.jsp");
                }
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
