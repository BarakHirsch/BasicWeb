package logic;

import java.util.ArrayList;
import models.Question;
import enums.Category;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.List;


public class Manager {

    public final FileManager<ArrayList<Question>> fileManager;
    private ArrayList<Question> questions;    

    public Manager() {
        fileManager = new FileManager<>("SavedGame.txt");
        try {
            questions = fileManager.Load();
        } catch (FileNotFoundException ex) {
            // Doesn't suppose to happen but ok...
        } catch(IOException ex) {
            System.err.println("Failed to read save file");
            questions = null;
        } catch ( ClassNotFoundException ex) {
            System.err.println("Failed to read data, ClassNotFoundException");
            questions = null;
        }
        // Some error occurd (maybe file not found and stuff) initiate a new save
        if ( questions == null ) {
           questions = new ArrayList<>();
        }
    }

    public void Save() {
        try{
            fileManager.Save(questions);
        } catch (FileNotFoundException ex) {
            System.err.println("Failed to find save file");
        } catch(IOException ex) {
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

    public TriviaGame startGame(List<Category> categories) {
        ArrayList<Question> filteredQuestions = new ArrayList<>();
        
        for (Question question : questions) {
            if (categories.contains(question.getCategory())) {
                filteredQuestions.add(question);
            }
        }
                
        return new TriviaGame(filteredQuestions);
    }
}
