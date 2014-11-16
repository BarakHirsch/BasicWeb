package logic;

import java.util.ArrayList;
import models.Question;

public class Manager {

    private final ArrayList<Question> questions;
    public final FileManager<ArrayList<Question>> fileManager;

    public Manager() {
        fileManager = new FileManager<>("SavedGame.txt");

        //TODO: Load Questions From File
        //questions = fileManager.Load();
        questions = new ArrayList<>();
    }

    public void Save() {
        fileManager.Save(questions);
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
}
