/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import helpers.UserHelper;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.User;

public class UserInfoServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        UserHelper.LoadUserToSession(request);

        request.setAttribute("User", UserHelper.getUser(request));

        request.getRequestDispatcher("UserInfo.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if ((request.getParameter("firstName").isEmpty()) || (request.getParameter("lastName").isEmpty())) {
            response.sendRedirect("Login.jsp");
        }

        User user = new User();

        String userName = request.getParameter("firstName") + " " + request.getParameter("lastName");

        user.setName(userName);

        Cookie userNameCookie = new Cookie("User", userName);
        userNameCookie.setMaxAge(60 * 60 * 24);
        response.addCookie(userNameCookie);

        UserHelper.LoadUserToSession(request, user);

        request.setAttribute("User", UserHelper.getUser(request));

        request.getRequestDispatcher("UserInfo.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
