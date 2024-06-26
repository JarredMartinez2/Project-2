package com.project2.triviaGame;

import org.junit.Test;

import static org.junit.Assert.*;

import com.project2.triviaGame.Database.entities.LeaderBoard;
import com.project2.triviaGame.Database.entities.Trivia;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    //Testing if the user value stored in the DB matches
    @Test
    public void getLbName() {
        LeaderBoard leaderboard = new LeaderBoard(5,"newuser");
        assertEquals("newuser", leaderboard.getLbName());
    }
    //Testing if the score value stored in the DB matches
    @Test
    public void getLbScore() {
        LeaderBoard leaderboard = new LeaderBoard(5,"newuser");
        assertEquals(5, leaderboard.getScore());
    }

    @Test
    public void getCategoryTriviaTest() {
       Trivia t = new Trivia("Thor", "Iron Man", "Island Boy",
                "Ronin", "Who weilds Mjollnir?", "Marvel");
       assertEquals("Marvel", t.getCategory());
    }
    @Test
    public void getCorrectAnswerTriviaTest() {
        Trivia t = new Trivia("Thor", "Iron Man", "Island Boy",
                "Ronin", "Who weilds Mjollnir?", "Marvel");
        assertEquals("Thor", t.getCorrectAnswer());
    }
}