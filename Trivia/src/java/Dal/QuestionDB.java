package Dal;

import enums.Category;
import enums.Difficulty;
import helpers.ParseHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.UUID;
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

        Question[] questionArray=null;
        try (Connection connection = DBUtil.getConnection()) {
            int sizeOfArray;
            int cnt = 0;
            Statement st = connection.createStatement();
            ResultSet size = st.executeQuery("SELECT COUNT(*) FROM Questions");
            size.next();
            sizeOfArray = size.getInt(1);
            questionArray = new Question[sizeOfArray];
            ResultSet rs = st.executeQuery("select * from Questions");

            while (rs.next()) {
                questionArray[cnt++] = new Question(UUID.fromString(rs.getString(1)), ParseHelper.parseDifficulty(Integer.toString(rs.getInt(2))), ParseHelper.parseCategory(Integer.toString(rs.getInt(2))), rs.getString(4), rs.getString(5));
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return questionArray;
    }

}
