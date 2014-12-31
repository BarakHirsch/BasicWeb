/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import helpers.ParseHelper;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.Manager;
import logic.TriviaGame;
import models.Question;

public class DeleteQuestionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Question[] questions = Manager.getInsance().getQuestions();

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet QuestionsServlet</title>");
            out.println("<style>");
            out.println("h3 {color:black;font: 14px  verdana, arial, helvetica;}");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");

            out.println("<h2>Select question to delete:</h2>");

            for (int i = 0; i < questions.length; i++) {
                Question question = questions[i];

                out.println("<form action=\"DeleteQuestionServlet\" method=\"POST\">");

                out.print("<h3>Category: " + question.getCategory() + " Difficulty: " + question.getDifficulty() + "</h3>");
                out.print("<h3>" + question.getQuestionText() + "</h3>");

                out.print("<input type=\"submit\" value=\"Delete\">");

                out.print("<input type=\"hidden\" name=\"QuestionIndex\" value=\"" + i + "\">");

                out.println("</form>");
            }

            out.println("</body>");
            out.println("</html>");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String indexString = request.getParameter("QuestionIndex");

        if (indexString == null) {
            doGet(request, response);
            return;
        }
        int index = ParseHelper.tryParseNumber(indexString);

        if (index == Integer.MAX_VALUE) {
            doGet(request, response);
            return;
        }
        Question question = Manager.getInsance().getQuestions()[index];

        Manager.getInsance().deleteQuestion(question);

        Manager.getInsance().Save();

        doGet(request, response);
    }
}
