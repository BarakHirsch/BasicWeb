/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import enums.*;

import java.io.IOException;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.Manager;
import logic.TriviaGame;
import helpers.ParseHelper;

public class StartGameServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HandleRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HandleRequest(request, response);
    }

    public void HandleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HashMap<Category, Difficulty> categoriesToPlay = new HashMap<>();

        for (Category cat : Category.values()) {
            if (request.getParameter("checkBox" + cat) != null) {
                categoriesToPlay.put(cat, ParseHelper.parseDifficulty(request.getParameter("radioButton" + cat)));
            }
        }
        TriviaGame currentGame = Manager.getInsance().startGame(categoriesToPlay);
        request.getSession().setAttribute("currGame", currentGame);
        response.sendRedirect("PlayServlet");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
