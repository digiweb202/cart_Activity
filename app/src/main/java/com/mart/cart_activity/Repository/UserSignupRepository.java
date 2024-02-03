package com.mart.cart_activity.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.mart.cart_activity.Dao.UserSignupDao;
import com.mart.cart_activity.Database.AppDatabase;
import com.mart.cart_activity.Entities.UserSignupEntities;
import com.mart.cart_activity.Operation.InsertUserAsyncTask;

import java.util.List;

public class UserSignupRepository {
    private UserSignupDao userSignupDao;
    private LiveData<List<UserSignupEntities>> allUsers;

    public UserSignupRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        userSignupDao = database.userSignupDao();
        allUsers = userSignupDao.getAllUsers();
    }

    public void insert(UserSignupEntities user) {
        new InsertUserAsyncTask(userSignupDao).execute(user);
    }
    public void updateUser(UserSignupEntities user) {
        new UpdateUserAsyncTask(userSignupDao).execute(user);
    }
    public LiveData<List<UserSignupEntities>> getAllUsers() {
        return allUsers;
    }
    public UserSignupEntities loginUser(String username, String password) {
        // Perform the login query on a background thread using AsyncTask or another mechanism
        // Here, I'm using a synchronous query for simplicity, but in a real app, you would want to use an asynchronous approach
        return userSignupDao.loginUser(username, password);
    }
    public LiveData<UserSignupEntities> getUserById(int userId) {
        return userSignupDao.getUserById(userId);
    }

    // Other repository methods

    private static class UpdateUserAsyncTask extends AsyncTask<UserSignupEntities, Void, Void> {
        private UserSignupDao userSignupDao;

        UpdateUserAsyncTask(UserSignupDao userDao) {
            this.userSignupDao = userDao;
        }

        @Override
        protected Void doInBackground(UserSignupEntities... users) {
            userSignupDao.updateUser(users[0]);
            return null;
        }
    }
    // ...
}
