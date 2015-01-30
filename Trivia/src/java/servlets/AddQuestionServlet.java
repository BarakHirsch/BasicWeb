package servlets;

import enums.Category;
import enums.Difficulty;
import helpers.ParseHelper;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.Manager;
import models.MultipleChoiceQuestion;
import models.Question;
import models.YesNoQuestion;

public class AddQuestionServlet extends HttpServlet {

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

    public void HandleRequest(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        Question que = null;

        Difficulty difficulty = ParseHelper.parseDifficulty(request.getParameter("difficulty"));
        Category category = ParseHelper.parseCategory(request.getParameter("category"));
        String questionText = request.getParameter("questionText");
        String questionType = request.getParameter("questionType");
        
        UUID id = UUID.randomUUID();

        switch (questionType) {
            case "Multiple":
                ArrayList<String> options = new ArrayList<>();
                options.add(request.getParameter("answer1"));
                options.add(request.getParameter("answer2"));
                options.add(request.getParameter("answer3"));
                int answerIndex = Integer.parseInt(request.getParameter("radioButtonTrue"));

                que = new MultipleChoiceQuestion(id, difficulty, category, questionText, options, options.get(answerIndex));
                break;

            case "Open":
                que = new Question(id, difficulty, category, questionText, request.getParameter("answer"));
                break;

            case "YesNo":
                boolean isTrue = request.getParameter("radioButtonYesNo").equalsIgnoreCase("Yes");
                que = new YesNoQuestion(id, difficulty, category, questionText, isTrue ? "Yes" : "No");
                break;
        }

        if (que != null) {
            Manager.getInsance().addQuestion(que);

            request.setAttribute("newQuestion", que);

            request.getRequestDispatcher("QuestionAdded.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("addQuestion.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
