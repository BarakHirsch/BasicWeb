package servlets;

import enums.*;
import helpers.UserHelper;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
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
            out.println("h4 {color:black;font: 18px  verdana, arial, helvetica;}");
            out.println("h5 {color:red;font: 18px  verdana, arial, helvetica;}");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");

            out.println("<h1>Game Over!</h1>");

            HashMap<Category, Integer> askedCount = currentGame.getAskedCount();
            HashMap<Category, Integer> answersCount = currentGame.getAnswersCount();

            for (Category category : currentGame.getCategories()) {
                out.println("<h3>In " + category + " you were asked " + askedCount.get(category) + " questions and answerd " + answersCount.get(category) + " correctly.</h3>");
            }

            out.println("<h4>Thank you for playing, " + UserHelper.getUserName(request) + "</h4>");

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
