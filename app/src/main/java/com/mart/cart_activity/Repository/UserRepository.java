package com.mart.cart_activity.Repository;


import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.mart.cart_activity.Dao.UserDao;
import com.mart.cart_activity.Database.Databases;
import com.mart.cart_activity.Entities.UserEntities;

import java.util.List;

public class UserRepository {

    private UserDao personDAO;
    private LiveData<List<UserEntities>> allPersons;

    public UserRepository(Databases Mydatabase) {
        personDAO = Mydatabase.getPersonDAO();
        allPersons = (LiveData<List<UserEntities>>) personDAO.getAllPerson();
    }

    public LiveData<List<UserEntities>> getAllPersons() {
        return allPersons;
    }

    public void insert(UserEntities user) {
        new InsertPersonAsyncTask(personDAO).execute(user);
    }

    private static class InsertPersonAsyncTask extends AsyncTask<UserEntities, Void, Void> {
        private UserDao personDAO;

        private InsertPersonAsyncTask(UserDao personDAO) {
            this.personDAO = personDAO;
        }

        @Override
        protected Void doInBackground(UserEntities... users) {
            personDAO.addPerson(users[0]);
            return null;
        }
    }
}
