package com.project2.triviaGame.Database;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.project2.triviaGame.Database.entities.LeaderBoard;
import com.project2.triviaGame.Database.entities.Trivia;
import com.project2.triviaGame.Database.entities.UserDB;
import com.project2.triviaGame.MainActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ProjectRepository {
    private userDao userDAO;
    private triviaDao triviaDao;
    private lbDao lbDao;
    private ArrayList<UserDB> alluserLogs;

    private static ProjectRepository repository;
    public ProjectRepository(Application application) {
        Project2Database db = Project2Database.getDatabase(application);
        this.userDAO = db.userDao();
        this.alluserLogs = (ArrayList<UserDB>) this.userDAO.getAllRecords();
    }

    public static ProjectRepository getRepository(Application application) {
        if (repository != null) {
            return repository;
        }
        Future<ProjectRepository> future = Project2Database.databaseWriteExecuter.submit(
                new Callable<ProjectRepository>() {
                    @Override
                    public ProjectRepository call() throws Exception {
                        return new ProjectRepository(application);
                    }
                }
        );
        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            Log.d(MainActivity.TAG, "problem getting repository, thread error");
        }
        return null;
    }

    public ArrayList<UserDB> getAlluserLogs() {
        Future<ArrayList<UserDB>> future = Project2Database.databaseWriteExecuter.submit(
                new Callable<ArrayList<UserDB>>() {
                    @Override
                    public ArrayList<UserDB> call() throws Exception {
                        return (ArrayList<UserDB>) userDAO.getAllRecords();
                    }
                }
        );
        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            Log.i(MainActivity.TAG, "Problem with getting all users from repository");
        }
        return null;
    }
    public void insertUser(UserDB... userDB) {
        Project2Database.databaseWriteExecuter.execute(() -> {
            userDAO.insert(userDB);
        });
    }

    public LiveData<UserDB> getUserByUserName(String userName) {
        return userDAO.getUserbyUserName(userName);
    }

    public List<Trivia> getallSet() {
        return triviaDao.getAllSets();
    }

    public void insertSet(Trivia... t) {
        Project2Database.databaseWriteExecuter.execute( () -> {
            triviaDao.insert(t);
        });
    }

    public void deleteSet(Trivia t) {
        Project2Database.databaseWriteExecuter.execute(() -> {
            triviaDao.delete(t);
        });
    }

    public List<Trivia> getCurrentSet(String category) {
        return triviaDao.getSet(category);
    }

    public List<LeaderBoard> getALlScores() {
        return lbDao.getAllScores();
    }

    public void insertLB(LeaderBoard... lb) {
        Project2Database.databaseWriteExecuter.execute(() -> {
            lbDao.insert(lb);
        });
    }

    public void deleteAll() {
        Project2Database.databaseWriteExecuter.execute(() -> {
            lbDao.deleteALl();
        });
    }
}