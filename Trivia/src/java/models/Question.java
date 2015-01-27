package models;

import enums.*;
import java.io.Serializable;
import java.util.UUID;

public class Question implements Serializable {

    public UUID id;
    private Difficulty difficulty;
    private Category category;
    private String questionText;
    private String correctAnswer;

    public Question(UUID id, Difficulty difficulty, Category category, String questionText, String answer) {
        this.id = id;
        this.difficulty = difficulty;
        this.category = category;
        this.questionText = questionText;
        this.correctAnswer = answer;
    }

    public String getType() {
        return "Question";
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public boolean verifyAnswer(String answer) {

        return correctAnswer.equalsIgnoreCase(answer);
    }

    public String getAnswer() {
        return this.correctAnswer;
    }
}
