package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            Cookie[] cookies = request.getCookies();

            for (Cookie c : cookies) 
                if ((c.getName().equals("UserName"))) 
                    c.setMaxAge(0);
                
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<style>");
            out.println("h3 {color:white;font: 14px  verdana, arial, helvetica;}");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.print("<h3>");
            out.print("You Have Successfully Logged Out.<a href=\"LoginServlet\" target=\"_self\">(Login)</a>");
            out.print("</h3>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
