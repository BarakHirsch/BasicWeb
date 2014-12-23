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
import logic.*;
import models.*;

/**
 *
 * @author Barda
 */
public class PlayServlet extends HttpServlet {

    private boolean wasCurrect;
    private boolean hasAnswerd;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet QuestionsServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet QuestionsServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        TriviaGame currentGame = (TriviaGame) request.getSession().getAttribute("currGame");

        if (currentGame == null) {
            response.sendRedirect("");
            return;
        }

        if (!currentGame.hasMoreQuestions()) {
            response.sendRedirect("GameEndedServlet");
            return;
        }

        Question que = currentGame.getCurrentQuestion();

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet QuestionsServlet</title>");
            out.println("</head>");
            out.println("<body>");

            if (hasAnswerd) {
                if (wasCurrect) {
                    out.println("<h2>The answer was currect</h2>");
                } else {
                    out.println("<h2>The answer was wrong</h2>");
                }
            }

            out.println("<h2>" + que.getQuestionText() + "</h2>");
            out.println("<form action=\"PlayServlet\" method=\"POST\">");

            out.println("<input type=\"text\" name=\"answer\" ></input>");
            
            out.println("<input type=\"submit\" value=\"Submit\">");

            out.println("</form>");

            out.println("</body>");
            out.println("</html>");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        TriviaGame currentGame = (TriviaGame) request.getSession().getAttribute("currGame");

        if (currentGame == null) {
            response.sendRedirect("");
            return;
        }

        String answer = request.getParameter("answer");

        hasAnswerd = true;
        wasCurrect = currentGame.CheckAnsewr(answer);
        
        if (!currentGame.hasMoreQuestions()) {
            response.sendRedirect("GameEndedServlet");
            return;
        }

        doGet(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
