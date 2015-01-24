package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.*;

public class PlayServlet extends HttpServlet {

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

    public void HandleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        TriviaGame currentGame = (TriviaGame) request.getSession().getAttribute("currGame");
        if (currentGame == null) {
            response.sendRedirect("");
            return;
        }
        if (!currentGame.hasMoreQuestions()) {
            response.sendRedirect("GameEndedServlet");
            return;
        }
        String answer = request.getParameter("answer");
        if (answer != null && !"".equals(answer)) {
            request.setAttribute("wasCorrect", currentGame.CheckAnsewr(answer));
            
            if (!currentGame.hasMoreQuestions()) {
                response.sendRedirect("GameEndedServlet");
                return;
            }
        }
        request.setAttribute("currQuestion", currentGame.getCurrentQuestion());
        request.getRequestDispatcher("PlayQuestion.jsp").forward(request, response);
    }
}
