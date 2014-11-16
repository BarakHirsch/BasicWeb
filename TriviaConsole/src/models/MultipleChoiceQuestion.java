package models;

import enums.Category;
import enums.Difficulty;
import java.util.ArrayList;
import java.util.List;

public class MultipleChoiceQuestion extends Question {

    protected List<String> options;
    protected int answerIndex;

    public MultipleChoiceQuestion(Difficulty difficulty, Category category, String questionText, List<String> options, int answerIndex) {
        super(difficulty, category, questionText);
        this.options = options;
        this.answerIndex = answerIndex;
    }
  
    protected MultipleChoiceQuestion(Difficulty difficulty, Category category, String questionText) {
        super(difficulty, category, questionText);
        options = new ArrayList<>();
    }

    public List<String> getOptions() {
        return options;
    }

    public int getAnswerIndex() {
        return answerIndex;
    }
}
