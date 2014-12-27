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

public class LoginServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        boolean isFirstTime = true;
        String firstName = "";
        String lastName = "";
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginServlet</title>");
            out.println("<style>");
            out.println("#loginSection{background-color:black;color:white;text-align:left;font: 10px  verdana, arial, helvetica;}");
            out.println("#header {background-color:black;color:white;text-align:center;padding:5px;font: 30px  verdana, arial, helvetica;}");
            out.println("#nav {line-height:30px;background-color:#eeeeee;height:300px;width:100px;float:left;padding:5px;font: 15px  verdana, arial, helvetica;}");
            out.println("#section {width:350px;float:left;padding:10px;font: 20px  verdana, arial, helvetica;}");
            out.println("#footer {background-color:black;color:white;clear:both;text-align:center;padding:5px;font: 20px  verdana, arial, helvetica;}");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie c : cookies) {
                    if ((c.getName().equals("firstName"))) {
                        isFirstTime = false;
                        firstName = c.getValue();
                    }
                    if ((c.getName().equals("lastName"))) {
                        lastName = c.getValue();
                    }
                }
                if (!isFirstTime) {
                    out.println("<div id=\"loginSection\">");
                    out.println("<br>");
                    out.println("Welcome Back, " + firstName + " " + lastName);
                    out.println("");
                    out.println("</div>");
                }
            }
            if ("".equals(firstName)) {
                out.println("<form action=\"LoginServlet\" method=\"post\">");
                out.println("<div id=\"loginSection\">");

                out.println("<br>");
                out.println("First name:<input type=\"text\" name=\"firstName\" value=\"\" >");
                out.println("Last name:<input type=\"text\" name=\"lastName\" value=\"\" >");
                out.println("<input type=\"submit\" name=\"submit\" value=\"Login\">");

                out.println("</div>");
                out.println("</form>");
            }

            out.println("<div id=\"header\">");
            out.println("<h1>Trivia</h1>");
            out.println("</div>");
            out.println("<div id=\"nav\">");
            out.println("<a href=\"generalInfo.html\" target=\"sectionFrame\">Information</a><br><br>");
            out.println("<a href=\"AnotherTrivia.html\" target=\"sectionFrame\">Links</a><br><br>");
            out.println("<a href=\"StartGameServlet\" target=\"sectionFrame\">Game start</a><br><br>");
            out.println("<a href=\"addQuestion.html\" target=\"sectionFrame\">Add a Question</a><br><br>");
            out.println("</div>");
            out.println("<div id=\"section\">");
            out.println("<iframe frameborder=\"0\" name=\"sectionFrame\" width=\"1000\" height=\"500\">");
            out.println("</iframe>");
            out.println("</div>");
            out.println("<div id=\"footer\">");
            out.println("<p>Copyright  ©  Barak & Aviran</p>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        String firstName = "";
        String lastName = "";
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginServlet</title>");
            out.println("<style>");
            out.println("#loginSection{background-color:black;color:white;text-align:left;font: 10px  verdana, arial, helvetica;}");
            out.println("#header {background-color:black;color:white;text-align:center;padding:5px;font: 30px  verdana, arial, helvetica;}");
            out.println("#nav {line-height:30px;background-color:#eeeeee;height:300px;width:100px;float:left;padding:5px;font: 15px  verdana, arial, helvetica;}");
            out.println("#section {width:350px;float:left;padding:10px;font: 20px  verdana, arial, helvetica;}");
            out.println("#footer {background-color:black;color:white;clear:both;text-align:center;padding:5px;font: 20px  verdana, arial, helvetica;}");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            if (cookies != null) {
                for (Cookie c : cookies) {
                    if ((c.getName().equals("firstName"))) {
                        firstName = c.getValue();
                    }
                    if ((c.getName().equals("lastName"))) {
                        lastName = c.getValue();
                    }
                }
                if (!("".equals(firstName))) {
                    out.println("<div id=\"loginSection\">");
                    out.println("<br>");
                    out.println("Welcome Back, " + firstName + " " + lastName);
                    out.println("");
                    out.println("</div>");
                }
            }
            if ("".equals(firstName)) {
                Cookie firstNameCookie = new Cookie("firstName", request.getParameter("firstName"));
                Cookie lastNameCookie = new Cookie("lastName", request.getParameter("lastName"));
                firstNameCookie.setMaxAge(60 * 60 * 24);
                lastNameCookie.setMaxAge(60 * 60 * 24);
                response.addCookie(firstNameCookie);
                response.addCookie(lastNameCookie);

                out.println("<div id=\"loginSection\">");

                out.println("<br>");
                out.println("Welcome Back, " + request.getParameter("firstName") + " " + request.getParameter("lastName"));
                out.println("");
                out.println("</div>");
            }
            out.println("<div id=\"header\">");
            out.println("<h1>Trivia</h1>");
            out.println("</div>");
            out.println("<div id=\"nav\">");
            out.println("<a href=\"generalInfo.html\" target=\"sectionFrame\">Information</a><br><br>");
            out.println("<a href=\"AnotherTrivia.html\" target=\"sectionFrame\">Links</a><br><br>");
            out.println("<a href=\"StartGameServlet\" target=\"sectionFrame\">Game start</a><br><br>");
            out.println("<a href=\"addQuestion.html\" target=\"sectionFrame\">Add a Question</a><br><br>");
            out.println("</div>");
            out.println("<div id=\"section\">");
            out.println("<iframe frameborder=\"0\" name=\"sectionFrame\" width=\"1000\" height=\"500\">");
            out.println("</iframe>");
            out.println("</div>");
            out.println("<div id=\"footer\">");
            out.println("<p>Copyright  ©  Barak & Aviran</p>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");

        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
