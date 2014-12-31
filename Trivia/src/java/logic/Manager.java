package logic;

import java.util.ArrayList;
import models.Question;
import enums.Category;
import enums.Difficulty;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

public class Manager {

    public final FileManager<ArrayList<Question>> fileManager;
    private ArrayList<Question> questions;
    private static Manager manager;

    public static Manager getInsance() {
        if (manager == null) {
            manager = new Manager();
        }

        return manager;
    }

    private Manager() {
        fileManager = new FileManager<>("SavedGame.dat");
        try {
            questions = fileManager.Load();
        } catch (FileNotFoundException ex) {
            //Doesn't suppose to happen but ok...
        } catch (IOException ex) {
            System.err.println("Failed to read save file");
            questions = null;
        } catch (ClassNotFoundException ex) {
            System.err.println("Failed to read data, ClassNotFoundException");
            questions = null;
        }
        // Some error occurd (maybe file not found and stuff) initiate a new save
        if (questions == null) {
            questions = new ArrayList<>();
        }
    }

    public void Save() {
        try {
            fileManager.Save(questions);
        } catch (FileNotFoundException ex) {
            System.err.println("Failed to find save file");
        } catch (IOException ex) {
            System.err.println("Failed to write to save file");
        }
    }

    public Question[] getQuestions() {
        return questions.toArray(new Question[questions.size()]);
    }

    public void deleteQuestion(Question question) {
        questions.remove(question);
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public TriviaGame startGame(Map<Category, Difficulty> categories) {
        ArrayList<Question> filteredQuestions = new ArrayList<>();

        for (Question question : questions) {
            Difficulty selectedDifficulty = categories.get(question.getCategory());

            if (selectedDifficulty != null && selectedDifficulty == question.getDifficulty()) {
                filteredQuestions.add(question);
            }
        }

        return new TriviaGame(categories.keySet(),filteredQuestions);
    }
}
