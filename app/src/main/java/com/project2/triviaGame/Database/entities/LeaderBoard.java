package com.project2.triviaGame.Database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.project2.triviaGame.Database.Project2Database;
import java.util.Objects;

@Entity(tableName = Project2Database.LB_TABLE)
public class LeaderBoard {
    @PrimaryKey(autoGenerate = true)
    private int lbKey;
    private int score;
    private String lbName;

    public LeaderBoard(int score, String lbName) {
        this.score = score;
        this.lbName = lbName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LeaderBoard that = (LeaderBoard) o;
        return lbKey == that.lbKey && score == that.score && Objects.equals(lbName, that.lbName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lbKey, score, lbName);
    }

    public int getLbKey() {
        return lbKey;
    }

    public void setLbKey(int lbKey) {
        this.lbKey = lbKey;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getLbName() {
        return lbName;
    }

    public void setLbName(String lbName) {
        this.lbName = lbName;
    }
}
