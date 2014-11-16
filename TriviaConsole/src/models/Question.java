package models;

import enums.*;

public class Question {

    private Difficulty difficulty;
    private Category category;
    private String questionText;

    public Question(Difficulty difficulty, Category category, String questionText) {
        this.difficulty = difficulty;
        this.category = category;
        this.questionText = questionText;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }
}
