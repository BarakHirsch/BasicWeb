/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import enums.Category;
import enums.Difficulty;

public class OpenQuestion extends Question {

    private String answer;

    public OpenQuestion(Difficulty difficulty, Category category, String questionText, String answer) {
        super(difficulty, category, questionText);
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
