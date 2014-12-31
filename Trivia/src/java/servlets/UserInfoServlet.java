/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserInfoServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        String userName = null;

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<style>");
            out.println("h3 {color:white;font: 14px  verdana, arial, helvetica;}");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");

            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie c : cookies) {
                    if ((c.getName().equals("UserName"))) {
                        userName = c.getValue();
                    }
                }
            }
            out.print("<h3>");

            if (userName == null) {
                out.print("Hello, Guest");
                out.println("<a href=\"LoginServlet\" target=\"_self\">(Login)</a>");
            } else {
                out.print("Hello, " + userName);
                out.println("<a href=\"LogoutServlet\" target=\"_self\">(Logout)</a>");
            }
            out.print("</h3>");
            out.println("</body>");
            out.println("</html>");

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if ((request.getParameter("firstName").isEmpty() ) || (request.getParameter("lastName").isEmpty()))
                response.sendRedirect("LoginServlet");
        
        Cookie userNameCookie = new Cookie("UserName", request.getParameter("firstName") +" "+ request.getParameter("lastName"));
        userNameCookie.setMaxAge(60 * 60 * 24);
        response.addCookie(userNameCookie);
       
        String userName = userNameCookie.getValue();

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<style>");
            out.println("h3 {color:white;font: 14px  verdana, arial, helvetica;}");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");

            out.print("<h3>");

            if (userName == null) {
                out.print("Hello, Guest");
                out.println("<a href=\"LoginServlet\" target=\"_self\">(Login)</a>");
            } else {
                out.print("Hello, " + userName);
                out.println("<a href=\"LogoutServlet\" target=\"_self\">(Logout)</a>");
            }
            out.print("</h3>");
            out.println("</body>");
            out.println("</html>");

        }
      
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
