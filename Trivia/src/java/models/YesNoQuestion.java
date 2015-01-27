package models;

import enums.Category;
import enums.Difficulty;
import java.util.Arrays;
import java.util.UUID;

public class YesNoQuestion extends MultipleChoiceQuestion {

    public YesNoQuestion(UUID id, Difficulty difficulty, Category category, String questionText, boolean isTrue) {
        super(id, difficulty, category, questionText, Arrays.asList(new String[]{"Yes", "No"}), isTrue ? 0 : 1);
    }

    @Override
    public String getType() {
        return "YesNoQuestion";
    }
}
