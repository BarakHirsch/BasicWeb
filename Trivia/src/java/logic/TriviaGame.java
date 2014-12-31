package logic;

import enums.Category;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import models.Question;

public class TriviaGame {

    private final List<Question> questionsInPlay;
    private final HashMap<Category, Integer> askedCount;
    private final HashMap<Category, Integer> answersCount;
    private final Set<Category> categories;
    private int curIndex;

    public TriviaGame(Set<Category> categories, List<Question> questionsInPlay) {
        this.questionsInPlay = questionsInPlay;
        this.categories = categories;
        curIndex = 0;

        answersCount = new HashMap<>();
        askedCount = new HashMap<>();

        for (Category category : categories) {
            answersCount.put(category, 0);
            askedCount.put(category, 0);
        }

        Collections.shuffle(this.questionsInPlay);
    }

    public boolean hasMoreQuestions() {
        return questionsInPlay.size() != curIndex;
    }

    public Question getCurrentQuestion() {
        if (questionsInPlay.size() == curIndex) {
            return null;
        }

        Question currQuestion = questionsInPlay.get(curIndex);

        return currQuestion;
    }

    public boolean CheckAnsewr(String answer) {
        Question currQuestion = questionsInPlay.get(curIndex);

        boolean wasCorrect = currQuestion.verifyAnswer(answer);

        Category category = currQuestion.getCategory();

        askedCount.put(category, askedCount.get(category) + 1);

        if (wasCorrect) {
            answersCount.put(category, answersCount.get(category) + 1);
        }

        curIndex++;

        return wasCorrect;
    }

    public HashMap<Category, Integer> getAnswersCount() {
        return answersCount;
    }

    public HashMap<Category, Integer> getAskedCount() {
        return askedCount;
    }

    public Set<Category> getCategories() {
        return categories;
    }
}
