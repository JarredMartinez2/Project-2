package com.project2.triviaGame.Database;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.project2.triviaGame.Database.entities.LeaderBoard;
import com.project2.triviaGame.Database.entities.UserDB;
import com.project2.triviaGame.Database.entities.Trivia;
import com.project2.triviaGame.MainActivity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {UserDB.class, Trivia.class, LeaderBoard.class}, version = 18, exportSchema = false)
public abstract class Project2Database extends RoomDatabase {

    public static final String LB_TABLE = "lbTable";
    private static final String DATABASENAME = "userDB_database";
    public static final String USER_DB_TABLE = "userDbTable";

    public static final String TRIVIA_TABLE = "triviaTable";

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
                triviaDao tDao = INSTANCE.triviaDao();
                lbDao lbDao = INSTANCE.lbDao();
                dao.deleteALl();
                UserDB admin = new UserDB("admin1", "admin1");
                admin.setAdmin(true);
                dao.insert(admin);
                UserDB testUser1 = new UserDB("testuser1", "testuser1");
                dao.insert(testUser1);
                Trivia q1 = new Trivia("Thor", "Iron Man", "Island Boy",
                        "Ronin", "Who wields Mjollnir?", "Marvel");
                tDao.insert(q1);
                Trivia q2 = new Trivia("Thanos", "Red Skull", "Mr.Beast",
                        "Darkseid", "Who snapped away half the world?", "Marvel");
                tDao.insert(q2);
                Trivia q3 = new Trivia("Iron Man", "Thor", "Hulk",
                        "Black Panther", "Which movie kicked off the Marvel Cinematic Universe?", "Marvel");
                tDao.insert(q3);
                Trivia q4 = new Trivia("Bucky", "Captain America", "Dr.C",
                        "Steve", "Who is the Winter Soldier?", "Marvel");
                tDao.insert(q4);
                Trivia q5 = new Trivia("vibranium", "MC Bedrock", "Tungsten",
                        "Titanium", "What metal is Black Panthers suit made of?", "Marvel");
                tDao.insert(q5);
                LeaderBoard lb = new LeaderBoard(4, "ABC");
                lbDao.insert(lb);

                Trivia t1 = new Trivia("dr c", "dr markov", "dr jotto", "dr r2", "who is the teacher of cst 338?", "CST338");
                tDao.insert(t1);
                Trivia t2 = new Trivia ("Tomas" , "William", "Nathan" ,"yoda", "who is the ta for the morning section?", "CST338");
                tDao.insert(t2);
                Trivia t3 = new Trivia ("java", "javascript", "javascriptisjava", "cobol", "What is the language of focus primarily for cst 338?", "CST338");
                tDao.insert(t3);
                Trivia t4 = new Trivia("BIT Building", "Chapman Science Building", "Zoom", "Death Star", "Where does CST 338 meet?", "CST338");
                tDao.insert(t4);
                Trivia t5 = new Trivia ("Mon and Wed","Wed and Fri","Monday","Thursday","What day or days does the class meet?", "CST338");
                tDao.insert(t5);
            });
        }
    };

    public abstract userDao userDao();

    public abstract triviaDao triviaDao();

    public abstract  lbDao lbDao();
}
