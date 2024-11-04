package controller;

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
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        String password = (String) session.getAttribute("password");

        User user=new User();
        user.setUsername(username);
        user.setPassword(password);
        UserDao userDao = new UserDao();
        try{
            if (userDao.validateUser(user)){
                ContactRequestDao dao = new ContactRequestDao();
                try {
                    List<ContactRequest> activeRequests = dao.getRequests(false);
                    List<ContactRequest> archivedRequests = dao.getRequests(true);
                    System.out.println(archivedRequests);
                    System.out.println(activeRequests);

                    request.setAttribute("activeRequests", activeRequests);
                    request.setAttribute("archivedRequests", archivedRequests);
                    request.getRequestDispatcher("requests.jsp").forward(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                    response.sendRedirect("login.jsp");
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
            else{
                response.sendRedirect("login.jsp");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        String password = (String) session.getAttribute("password");

        User user=new User();
        user.setUsername(username);
        user.setPassword(password);
        UserDao userDao = new UserDao();
        try{
            if(userDao.validateUser(user)){
                int requestId = Integer.parseInt(request.getParameter("requestId"));
                ContactRequestDao dao = new ContactRequestDao();
                try {
                    dao.archiveRequest(requestId);
                    response.sendRedirect("adminrequests");
                } catch (SQLException e) {
                    e.printStackTrace();
                    response.sendRedirect("requests.jsp");
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

