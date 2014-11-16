/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import enums.Category;
import enums.Difficulty;

public class YesNoQuestion extends MultipleChoiceQuestion {

    public YesNoQuestion(Difficulty difficulty, Category category, String questionText, boolean isTrue) {
        super(difficulty, category, questionText);

        options.add("Yes");
        options.add("No");
        
        answerIndex = isTrue ? 0 : 1;
    }
}
