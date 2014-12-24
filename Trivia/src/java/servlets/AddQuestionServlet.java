/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import enums.Category;
import enums.Difficulty;
import helpers.ParseHelper;
import java.io.IOException;
import java.io.PrintWriter;
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

    private Question que;
    private Difficulty diff;
    private Category cat;
    private String questionText;
    private String questionModel;
    private ArrayList<String> options;

    //public Question(Difficulty difficulty, Category category, String questionText, String answer) {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        switch (questionModel) {
            case "Multiple":
                options = new ArrayList<>();
                options.add(request.getParameter("answer1"));
                options.add(request.getParameter("answer2"));
                options.add(request.getParameter("answer3"));
                que = new MultipleChoiceQuestion(diff, cat, questionText, options, Integer.parseInt(request.getParameter("radioButtonTrue")));
                break;

            case "Open":
                que = new Question(diff, cat, questionText, request.getParameter("answer"));
                break;

            case "YesNo":
                boolean isTrue = request.getParameter("radioButtonYesNo").equalsIgnoreCase("Yes");
                que = new YesNoQuestion(diff, cat, questionText, isTrue);
                break;
        }
        if (que != null) {
            Manager.getInsance().addQuestion(que);
            Manager.getInsance().Save();
            
        }

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddQuestionServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddQuestionServlet at " + "question added" + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.diff = ParseHelper.parseDifficulty2(request.getParameter("radioButtonDiff"));
        this.cat = ParseHelper.parseCategory2(request.getParameter("radioButtonCat"));
        this.questionText = request.getParameter("question");
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddQuestionServlet</title>");
            out.println("<style>");
            out.println("#h2_{font: 10px  verdana, arial, helvetica;}");
            out.println("</style>");
            out.println("");
            out.println("</head>");
            out.println("<body>");
            out.println("<form action=\"AddQuestionServlet\" method=\"Get\">");
            out.println("<div id=\"h2_\">");
            switch (request.getParameter("radioButtonType")) {
                case "Multiple":
                    this.questionModel = "Multiple";

                    out.println("<h2>Please insert 3 optional answers for your question.</h2>");
                    out.println("<h2>Please pick the true answer with the radio button at the right.</h2>");
                    out.println("<h2>Answer 1: <input type=\"text\" name=\"answer1\" value=\"\"> <input type=\"radio\" name=\"radioButtonTrue\" value=\"0\"> </h2>");
                    out.println("<h2>Answer 2: <input type=\"text\" name=\"answer2\" value=\"\"> <input type=\"radio\" name=\"radioButtonTrue\" value=\"1\"> </h2>");
                    out.println("<h2>Answer 3: <input type=\"text\" name=\"answer3\" value=\"\"> <input type=\"radio\" name=\"radioButtonTrue\" value=\"2\"> </h2>");
                    out.println("");
                    break;
                case "Open":
                    this.questionModel = "Open";
                    out.println("<h2>Please insert the answer for your question</h2>");
                    out.println("<input type=\"text\" name=\"answer\" value=\"\"><br>");
                    break;
                case "YesNo":
                    this.questionModel = "YesNo";
                    out.println("<h2>Please insert the answer for your question</h2>");
                    out.println("<input type=\"radio\" name=\"radioButtonYesNo\" value=\"Yes\" checked>Yes<br>");
                    out.println("<input type=\"radio\" name=\"radioButtonYesNo\" value=\"No\" >No<br>");

                    break;
            }
            out.println("<h2><input type=\"submit\" name=\"submit\" value=\"click here\"></h2>");
            out.println("</div>");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
