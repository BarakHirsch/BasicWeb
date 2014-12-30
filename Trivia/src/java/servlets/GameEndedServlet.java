package servlets;

import enums.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.TriviaGame;

public class GameEndedServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            TriviaGame currentGame = (TriviaGame) request.getSession().getAttribute("currGame");

            if (currentGame == null) {
                response.sendRedirect("");
                return;
            }
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Game ended Servlet</title>");
            out.println("<style>");
            out.println("h1 {color:black;font: 40px  verdana, arial, helvetica;}");
            out.println("h3 {color:black;font: 20px  verdana, arial, helvetica;}");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");

            out.println("<h1>Game Over!</h1>");

            for (Map.Entry<Category, Integer> answerCount : currentGame.getAnswersCount().entrySet()) {
                out.println("<h3>In " + answerCount.getKey()+ " category, you answerd " + answerCount.getValue()+ " questions correctly.</h3>");
            }

            out.println("</body>");
            out.println("</html>");

            request.getSession().removeAttribute("currGame");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
