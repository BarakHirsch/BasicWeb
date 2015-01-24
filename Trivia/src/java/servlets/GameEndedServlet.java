package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.TriviaGame;

public class GameEndedServlet extends HttpServlet {

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
        }

        request.getSession().removeAttribute("currGame");
        
        request.setAttribute("currGame", currentGame);

        request.getRequestDispatcher("GameEnded.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
