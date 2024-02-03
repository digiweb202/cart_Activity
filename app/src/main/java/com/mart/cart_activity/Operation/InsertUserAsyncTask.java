package com.mart.cart_activity.Operation;

import android.os.AsyncTask;

import com.mart.cart_activity.Dao.UserSignupDao;
import com.mart.cart_activity.Entities.UserSignupEntities;

public class InsertUserAsyncTask extends AsyncTask<UserSignupEntities, Void, Void> {
    private UserSignupDao userSignupDao;

    public InsertUserAsyncTask(UserSignupDao userDao) {
        this.userSignupDao = userDao;
    }

    @Override
    protected Void doInBackground(UserSignupEntities... users) {
        userSignupDao.insert(users[0]);
        return null;
    }
}