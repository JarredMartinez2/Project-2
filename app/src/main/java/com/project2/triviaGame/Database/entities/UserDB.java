package com.project2.triviaGame.Database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.project2.triviaGame.Database.Project2Database;

import java.util.Objects;

@Entity(tableName = Project2Database.USER_DB_TABLE)
public class UserDB {
    @PrimaryKey(autoGenerate = true)
    private int userId;
    private String userName;
    private String passWord;
    private boolean isAdmin;

    public UserDB(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
        this.isAdmin = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDB userDB = (UserDB) o;
        return userId == userDB.userId && isAdmin == userDB.isAdmin && Objects.equals(userName, userDB.userName) && Objects.equals(passWord, userDB.passWord);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userName, passWord, isAdmin);
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
