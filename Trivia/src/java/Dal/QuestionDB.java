package Dal;

import enums.Category;
import enums.Difficulty;
import helpers.ParseHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import models.MultipleChoiceQuestion;
import models.Question;
import models.YesNoQuestion;

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

            if (question instanceof MultipleChoiceQuestion) {
                SaveOptions(connection, question.getId().toString(), ((MultipleChoiceQuestion) question).getOptions());
            }

            statement.execute();

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void SaveOptions(Connection connection, String toString, List<String> options) throws SQLException {
        for (String option : options) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Options (question_id, option_text) VALUES (?,?)");

            statement.setString(1, toString);
            statement.setString(2, option);

            statement.execute();
        }
    }

    public void deleteQuestion(String id) {

        try (Connection connection = DBUtil.getConnection()) {

            PreparedStatement del = connection.prepareStatement("Delete from Questions WHERE id=?");
            PreparedStatement delOpts = connection.prepareStatement("Delete from options WHERE question_id=?");

            del.setString(1, id);
            delOpts.setString(1, id);

            delOpts.execute();
            del.execute();

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Question[] getAllQuestions() {

        ArrayList<Question> questionArray = new ArrayList<>();
        try (Connection connection = DBUtil.getConnection()) {

            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select id, difficulty, category, questionText, correctAnswer, type from Questions");

            while (rs.next()) {
                Question question = CreateQuestion(connection, 
                        UUID.fromString(rs.getString(1)),
                        ParseHelper.parseDifficulty(Integer.toString(rs.getInt(2))),
                        ParseHelper.parseCategory(Integer.toString(rs.getInt(3))),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6));
                
                questionArray.add(question);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return questionArray.toArray(new Question[0]);
    }

    private Question CreateQuestion(Connection connection, UUID id, Difficulty difficulty, Category Category, String questionText, String answer, String type) throws SQLException {
        switch (type) {
            case "MultipleChoiceQuestion":

                List<String> options = SelectOptions(connection, id);

                return new MultipleChoiceQuestion(id, difficulty, Category, questionText, options, answer);

            case "YesNoQuestion": {
                return new YesNoQuestion(id, difficulty, Category, questionText, answer);
            }
            default:
                return new Question(id, difficulty, Category, questionText, answer);
        }
    }

    private List<String> SelectOptions(Connection connection, UUID id) throws SQLException {
        PreparedStatement prepareStatement = connection.prepareStatement("Select option_text FROM Options WHERE question_id=?");

        ArrayList<String> options = new ArrayList<>();

        prepareStatement.setString(1, id.toString());
        
        ResultSet rs = prepareStatement.executeQuery();

        while (rs.next()) {
            options.add(rs.getString(1));
        }
        return options;
    }
}
