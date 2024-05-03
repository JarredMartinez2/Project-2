package com.project2.triviaGame.Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.project2.triviaGame.Database.entities.LeaderBoard;
import com.project2.triviaGame.Database.entities.Trivia;

import java.util.List;

@Dao
public interface lbDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(LeaderBoard... leaderBoards);
    @Query("Delete from " + Project2Database.LB_TABLE)
    void deleteALl();
    @Query("Select * from " + Project2Database.LB_TABLE )
    List<LeaderBoard> getAllScores();
}
