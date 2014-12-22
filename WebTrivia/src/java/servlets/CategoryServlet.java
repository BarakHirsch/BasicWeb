/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import enums.*;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CategoryServlet extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Game category picking form</title>");
            out.println("<meta charset=\"UTF-8\">");
            out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"gameCategory/GameCatCSS.css\" media=\"screen\">");
            out.println("</head>");
            out.println("<body>");
            out.println("<form action=\"ProcessGameServlet\" method=\"POST\">");
            out.println("<div id=\"main_container\">");
            out.println("<div id=\"logo\"><a href=\"http://localhost:8084/WebTrivia/TriviaHomePage/TriviaHomePage.html\"><img src=\"gameCategory/images/trivia-challenge-small.jpg   \" alt=\"\" title=\"\" border=\"0\"  ></a></div>");
            for (Category cat : Category.values()) {
                if (cat.name().equalsIgnoreCase("None")) {
                    continue;
                }
                
                out.println("<div id=\"main_content\">");
                out.println("<h2>"+ cat +"</h2>");
                out.println("<img src=\"gameCategory/images/"+ cat +".jpg\" alt=\"\" title=\"\" class=\"left_img\">");
                out.println("<br>");
                out.println("Please choose difficulty for your questions:<br>");
                out.println("<input type=\"radio\" name=\"radioButton"+cat+ "\" value=\"Easy\" checked> Easy<br><br>");
                out.println("<input type=\"radio\" name=\"radioButton"+cat+"\" value=\"Medium\" > Medium<br><br>");
                out.println("<input type=\"radio\" name=\"radioButton"+cat+"\" value=\"Hard\"> Hard<br><br>");
                out.println("<div align=\"center\">");
                out.println("<input type=\"checkbox\" name=\"checkBox"+cat+"\" value=\""+cat+"Check\">Check if you like to play in this category<br>");
                out.println("</div>");
                out.println("</div>");
                out.println("<hr>");
                
            }
            
            out.println("<input type=\"submit\" value=\"Submit\">");
            out.println("</div>");
            out.println("</form>");
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
