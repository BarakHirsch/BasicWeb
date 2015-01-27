package logic;

import Dal.QuestionDB;
import java.util.ArrayList;
import models.Question;
import enums.Category;
import enums.Difficulty;
import java.sql.SQLException;
import java.util.Map;

public class Manager {

    public final QuestionDB questionsDb;
    private ArrayList<Question> questions;
    private static Manager manager;

    public static Manager getInsance() {
        if (manager == null) {
            manager = new Manager();
        }

        return manager;
    }

    private Manager() {
        questionsDb = new QuestionDB();
    }

    public Question[] getQuestions() {
        return questionsDb.getAllQuestions();
    }

    public void deleteQuestion(Question question) {
        questionsDb.deleteQuestion(question);
    }

    public void addQuestion(Question question) {
        questionsDb.addQuestion(question);
    }

    public TriviaGame startGame(Map<Category, Difficulty> categories) {
        ArrayList<Question> filteredQuestions = new ArrayList<>();

        for (Question question : questions) {
            Difficulty selectedDifficulty = categories.get(question.getCategory());

            if (selectedDifficulty != null && selectedDifficulty == question.getDifficulty()) {
                filteredQuestions.add(question);
            }
        }

        return new TriviaGame(categories.keySet(), filteredQuestions);
    }
}
