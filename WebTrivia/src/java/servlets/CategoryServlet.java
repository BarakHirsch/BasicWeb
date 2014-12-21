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

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
                out.println("<img src=\"gameCategory/images/pic1.jpg\" alt=\"\" title=\"\" class=\"left_img\">");
                out.println("<br>");
                out.println("Please choose difficulty for your questions:<br>");
                out.println("<input type=\"radio\" name=\"radioButton1\" value=\"Easy\" checked> Easy<br><br>");
                out.println("<input type=\"radio\" name=\"radioButton1\" value=\"Medium\" > Medium<br><br>");
                out.println("<input type=\"radio\" name=\"radioButton1\" value=\"Hard\"> Hard<br><br>");
                out.println("<div align=\"center\">");
                out.println("<input type=\"checkbox\" name=\"GameCategory1\" value=\"GameCategory1\">Check if you like to play in this category<br>");
                out.println("</div>");
                out.println("</div>");
                out.println("<hr>");
                
            }
            
            out.println("<input type=\"submit\" value=\"Submit\">");
            out.println("</div>");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");

            for (Category cat : Category.values()) {

                if (cat.name().equalsIgnoreCase("None")) {
                    continue;
                }

            }

        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
