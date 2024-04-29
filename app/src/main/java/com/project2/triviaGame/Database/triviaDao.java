package com.project2.triviaGame.Database;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.project2.triviaGame.Database.entities.UserDB;
import com.project2.triviaGame.Database.entities.Trivia;

import java.util.List;

public interface triviaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Trivia... T);
    @Delete
    void delete(UserDB user);
    @Query("Select * from " + Project2Database.TRIVIA_TABLE + " Order by setId")
    List<Trivia> getAllRecords();

    @Query("Select wrongAnswer from " + Project2Database.TRIVIA_TABLE)
    List<Trivia> allWrongAnswers();
}
