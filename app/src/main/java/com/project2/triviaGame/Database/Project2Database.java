package com.project2.triviaGame.Database;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.project2.triviaGame.Database.entities.UserDB;
import com.project2.triviaGame.MainActivity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {UserDB.class}, version = 1, exportSchema = false)
public abstract class Project2Database extends RoomDatabase {

    private static final String DATABASENAME = "userDB_database";
    public static final String USER_DB_TABLE = "userDbTable";

    private static volatile Project2Database INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;

    final static ExecutorService databaseWriteExecuter = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    static Project2Database getDatabase(final Context content) {
        if (INSTANCE == null) {
            synchronized (Project2Database.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(content.getApplicationContext(),
                            Project2Database.class,
                                    DATABASENAME
                            )
                            .fallbackToDestructiveMigration()
                            .addCallback(addDefaultValues)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static final RoomDatabase.Callback addDefaultValues = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            Log.i(MainActivity.TAG, "DATABASE MADE!");
            databaseWriteExecuter.execute( () -> {
                userDao dao = INSTANCE.userDao();
                dao.deleteALl();
                UserDB admin = new UserDB("admin1", "admin1");
                admin.setAdmin(true);
                dao.insert(admin);

                UserDB testUser1 = new UserDB("testuser1", "testuser1");
                dao.insert(testUser1);
            });
        }
    };

    public abstract userDao userDao();
}
