package servlets;

import enums.Category;
import enums.Difficulty;
import helpers.ParseHelper;
import java.io.IOException;
import java.util.ArrayList;
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

    public void HandleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, NumberFormatException, IOException {
        Question que = null;

        Difficulty difficulty = ParseHelper.parseDifficulty(request.getParameter("difficulty"));
        Category category = ParseHelper.parseCategory(request.getParameter("category"));
        String questionText = request.getParameter("questionText");
        String questionType = request.getParameter("questionType");

        switch (questionType) {
            case "Multiple":
                ArrayList<String> options = new ArrayList<>();
                options.add(request.getParameter("answer1"));
                options.add(request.getParameter("answer2"));
                options.add(request.getParameter("answer3"));
                int answerIndex = Integer.parseInt(request.getParameter("radioButtonTrue"));

                que = new MultipleChoiceQuestion(difficulty, category, questionText, options, answerIndex);
                break;

            case "Open":
                que = new Question(difficulty, category, questionText, request.getParameter("answer"));
                break;

            case "YesNo":
                boolean isTrue = request.getParameter("radioButtonYesNo").equalsIgnoreCase("Yes");
                que = new YesNoQuestion(difficulty, category, questionText, isTrue);
                break;
        }

        if (que != null) {
            Manager.getInsance().addQuestion(que);
            Manager.getInsance().Save();

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
