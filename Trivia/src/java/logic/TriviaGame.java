package logic;

import java.util.Collections;
import java.util.List;
import models.Question;

public class TriviaGame {

    private final List<Question> questionsInPlay;
    private int curIndex;

    public TriviaGame(List<Question> questionsInPlay) {
        this.questionsInPlay = questionsInPlay;
        curIndex = 0;
        
        Collections.shuffle(this.questionsInPlay);
    }
    
    public boolean hasMoreQuestions()
    {
        return questionsInPlay.size() != curIndex;
    }

    public Question getNextQuestion() {
        if (questionsInPlay.size() == curIndex) {
            return null;
        }

        Question currQuestion = questionsInPlay.get(curIndex);
        curIndex++;
        
        return currQuestion;
    }
}
