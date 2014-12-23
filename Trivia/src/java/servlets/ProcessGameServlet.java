/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import enums.*;
import models.*;
import helpers.ParseHelper;
import java.util.ArrayList;
import logic.*;
import java.util.Enumeration;
import javax.servlet.http.HttpSession;

public class ProcessGameServlet extends HttpServlet {

    Manager manager = new Manager();

    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ArrayList<Category> categoriesToPlay = new ArrayList<>();
        for (Category cat : Category.values()) {
            if (request.getParameter("checkBox" + cat) != null) {
                categoriesToPlay.add(cat);
            }
        }

        TriviaGame currentGame = manager.startGame(categoriesToPlay);
        request.getSession().setAttribute("currGame", currentGame);
        response.sendRedirect("QuestionsServlet");

        
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
