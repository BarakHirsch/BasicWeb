package models;

import enums.Category;
import enums.Difficulty;
import java.util.List;
import java.util.UUID;

public class MultipleChoiceQuestion extends Question {

    protected List<String> options;

    public MultipleChoiceQuestion(UUID id, Difficulty difficulty, Category category, String questionText, List<String> options, int answerIndex) {
        super(id, difficulty, category, questionText, options.get(answerIndex));
        this.options = options;
    }

    public List<String> getOptions() {
        return options;
    }

    @Override
    public String getType() {
        return "MultipleChoiceQuestion";
    }
}
