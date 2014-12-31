package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.*;
import models.*;

public class PlayServlet extends HttpServlet {

    private boolean wasCorrect;
    private boolean hasAnswerd;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet QuestionsServlet</title>");

            out.println("<style>");
            out.println("h2 {color:black;font: 30px  verdana, arial, helvetica;}");
            out.println("h3 {color:black;font: 20px  verdana, arial, helvetica;}");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");

            if (hasAnswerd) {
                if (wasCorrect) {
                    out.println("<h2>The answer was correct!</h2>");
                } else {
                    out.println("<h2>The answer was wrong!</h2>");
                }
            }

            out.println("<h3>" + que.getQuestionText() + "</h3>");
            out.println("<form action=\"PlayServlet\" method=\"POST\">");

            if (que instanceof MultipleChoiceQuestion) {
                MultipleChoiceQuestion multi = (MultipleChoiceQuestion) que;
                for (String option : multi.getOptions()) {
                    out.println("<h4><input type=\"radio\" name=\"answer\" value =\"" + option + "\" required>" + option + "</h4>");
                }

            } else {
                out.println("<input type=\"text\" name=\"answer\" /> ");
            }
            out.println("<br>");
            out.println("<input type=\"submit\" value=\"Submit\"> ");

            out.println("</form>");
            out.println("<br>");
            out.println("<br>");
            
            out.println("<form action=\"GameEndedServlet\">");
            out.println("<input type=\"submit\" value=\"Quit\">");
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

        if (answer != null && !"".equals(answer)) {
            hasAnswerd = true;
            wasCorrect = currentGame.CheckAnsewr(answer);

            if (!currentGame.hasMoreQuestions()) {
                response.sendRedirect("GameEndedServlet");
                return;
            }
        }

        doGet(request, response);
    }
}
