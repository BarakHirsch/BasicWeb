package models;

import enums.Category;
import enums.Difficulty;
import java.util.Arrays;

public class YesNoQuestion extends MultipleChoiceQuestion {

    public YesNoQuestion(Difficulty difficulty, Category category, String questionText, boolean isTrue) {
        super(difficulty, category, questionText, Arrays.asList(new String[]{"Yes", "No"}), isTrue ? 1 : 2);
    }
}
