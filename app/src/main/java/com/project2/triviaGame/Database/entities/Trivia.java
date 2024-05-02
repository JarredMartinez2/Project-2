package com.project2.triviaGame.Database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.project2.triviaGame.Database.Project2Database;

import java.util.Objects;

@Entity(tableName = Project2Database.TRIVIA_TABLE)
public class Trivia {
    @PrimaryKey(autoGenerate = true)
    private int setId;
    private String correctAnswer;
    private String wrongAnswer;
    private String wrongAnswer2;
    private String wrongAnswer3;
    private String question;
    private String category;
    public Trivia(String correctAnswer, String wrongAnswer, String wrongAnswer2, String wrongAnswer3, String question, String category) {
        this.correctAnswer = correctAnswer;
        this.wrongAnswer = wrongAnswer;
        this.wrongAnswer2 = wrongAnswer2;
        this.wrongAnswer3 = wrongAnswer3;
        this.question = question;
        this.category = category;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trivia trivia = (Trivia) o;
        return setId == trivia.setId && Objects.equals(correctAnswer, trivia.correctAnswer) && Objects.equals(wrongAnswer, trivia.wrongAnswer) && Objects.equals(wrongAnswer2, trivia.wrongAnswer2) && Objects.equals(wrongAnswer3, trivia.wrongAnswer3) && Objects.equals(question, trivia.question) && Objects.equals(category, trivia.category);
    }
    @Override
    public int hashCode() {
        return Objects.hash(setId, correctAnswer, wrongAnswer, wrongAnswer2, wrongAnswer3, question, category);
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getSetId() {
        return setId;
    }

    public void setSetId(int setId) {
        this.setId = setId;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getWrongAnswer() {
        return wrongAnswer;
    }

    public void setWrongAnswer(String wrongAnswer) {
        this.wrongAnswer = wrongAnswer;
    }

    public String getWrongAnswer2() {
        return wrongAnswer2;
    }

    public void setWrongAnswer2(String wrongAnswer2) {
        this.wrongAnswer2 = wrongAnswer2;
    }

    public String getWrongAnswer3() {
        return wrongAnswer3;
    }

    public void setWrongAnswer3(String wrongAnswer3) {
        this.wrongAnswer3 = wrongAnswer3;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}