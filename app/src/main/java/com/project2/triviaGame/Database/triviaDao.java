package com.project2.triviaGame.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.project2.triviaGame.Database.entities.Trivia;

import java.util.List;
@Dao
public interface triviaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Trivia... T);
    @Delete
    void delete(Trivia T);
    @Query("Select * from " + Project2Database.TRIVIA_TABLE)
    List<Trivia> getAllSets();
    @Query("Delete from " + Project2Database.TRIVIA_TABLE)
    void deleteALl();
    @Query("Select * from " + Project2Database.TRIVIA_TABLE + " Where category == :category")
    List<Trivia> getSet(String category);
}
