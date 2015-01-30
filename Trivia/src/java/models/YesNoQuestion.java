package models;

import enums.Category;
import enums.Difficulty;
import java.util.Arrays;
import java.util.UUID;

public class YesNoQuestion extends MultipleChoiceQuestion {

    public YesNoQuestion(UUID id, Difficulty difficulty, Category category, String questionText, String isTrue) {
        super(id, difficulty, category, questionText, Arrays.asList(new String[]{"Yes", "No"}), isTrue);
    }

    @Override
    public String getType() {
        return "YesNoQuestion";
    }
}
