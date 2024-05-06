package com.project2.triviaGame.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.project2.triviaGame.Database.entities.UserDB;
import java.util.List;


@Dao
public interface userDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(UserDB... user);

    @Delete
    void delete(UserDB user);
    @Query("Select * from " + Project2Database.USER_DB_TABLE + " Order by userName")
    List<UserDB> getAllRecords();
    @Query("Delete from " + Project2Database.USER_DB_TABLE)
    void deleteALl();
    @Query("SELECT * from " + Project2Database.USER_DB_TABLE + " WHERE userName == :userName")
    LiveData<UserDB> getUserbyUserName(String userName);
}
