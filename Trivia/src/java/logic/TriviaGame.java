package logic;

import enums.Category;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import models.Question;

public class TriviaGame {

    private final List<Question> questionsInPlay;
    private final HashMap<Category, Integer> answersCount;
    private int curIndex;

    public TriviaGame(List<Question> questionsInPlay) {
        this.questionsInPlay = questionsInPlay;
        curIndex = 0;

        answersCount = new HashMap<>();

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

        boolean wasCurrect = currQuestion.verifyAnswer(answer);

        Category category = currQuestion.getCategory();

        Integer oldCount = answersCount.get(category);

        if (oldCount == null) {
            answersCount.put(category, 0);
        }

        if (wasCurrect) {
            answersCount.put(category, answersCount.get(category) + 1);
        }
        
        curIndex++;
        
        return wasCurrect;
    }
}
