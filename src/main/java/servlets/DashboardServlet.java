package servlets;

import dao.RequestDao;
import dao.UserDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Request;
import model.User;

import java.io.IOException;
import java.util.List;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (isUserAuthenticated(request, response)) {
            RequestDao dao = new RequestDao();

            List<Request> activeRequests = dao.fetchRequests(false);
            List<Request> archivedRequests = dao.fetchRequests(true);

            request.setAttribute("activeRequests", activeRequests);
            request.setAttribute("archivedRequests", archivedRequests);
            request.getRequestDispatcher("dashboard.jsp").forward(request, response);

        } else {
            response.sendRedirect("login.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (isUserAuthenticated(request, response)) {
            int checkRequestType = Integer.parseInt(request.getParameter("action"));
            if (checkRequestType==1) {
                int requestId = Integer.parseInt(request.getParameter("requestId"));
                RequestDao dao = new RequestDao();

                    dao.changeStatus(requestId,false);
                response.sendRedirect("dashboard");

            } else {
                int requestId = Integer.parseInt(request.getParameter("requestId"));
                RequestDao dao = new RequestDao();

                    dao.changeStatus(requestId,true);
                response.sendRedirect("dashboard");

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

        return userDao.isvalidUser(user);
    }
}

