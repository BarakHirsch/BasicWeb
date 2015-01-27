/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import helpers.ParseHelper;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.Manager;
import models.Question;

public class DeleteQuestionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Question[] questions = Manager.getInsance().getQuestions();

        request.setAttribute("questions", questions);
        request.getRequestDispatcher("DeleteQuestion.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HandleRequest(request, response);
    }

    public void HandleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String indexString = request.getParameter("QuestionIndex");

        if (indexString != null) {

            int index = ParseHelper.tryParseNumber(indexString);

            if (index != Integer.MAX_VALUE) {
                Question question = Manager.getInsance().getQuestions()[index];
                Manager.getInsance().deleteQuestion(question);
            }
        }

        doGet(request, response);
    }
}
