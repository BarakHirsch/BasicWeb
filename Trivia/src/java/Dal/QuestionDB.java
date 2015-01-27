package Dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import models.Question;

public class QuestionDB {

    public void addQuestion(Question question) {
        try (Connection connection = DBUtil.getConnection()) {
            String command = "INSERT INTO Questions "
                    + "(id, difficulty, category, questionText, correctAnswer, type) "
                    + "VALUES (?,?,?,?,?,?)";

            PreparedStatement statement = connection.prepareStatement(command);

            statement.setString(1, question.getId().toString());
            statement.setInt(2, question.getDifficulty().ordinal());
            statement.setInt(3, question.getCategory().ordinal());
            statement.setString(4, question.getQuestionText());
            statement.setString(5, question.getCorrectAnswer());
            statement.setString(6, question.getType());

            statement.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteQuestion(Question question) {
        try {
            //TODO
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Question[] getAllQuestions() {
        try {
            //TODO
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
