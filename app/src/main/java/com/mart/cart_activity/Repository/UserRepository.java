package com.mart.cart_activity.Repository;


import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.mart.cart_activity.Dao.UserDao;
import com.mart.cart_activity.Database.AppDatabase;
import com.mart.cart_activity.Entities.UserEntities;

import java.util.List;

public class UserRepository {
//
//    private UserDao personDAO;
//    private LiveData<List<UserEntities>> allPersons;
//
//    public UserRepository(AppDatabase Mydatabase) {
//        personDAO = Mydatabase.getPersonDAO();
//        allPersons = personDAO.getAllPerson();
//    }
//
//    public LiveData<List<UserEntities>> getAllPersons() {
//        return allPersons;
//    }
//
//    public void insert(UserEntities user) {
//        new InsertPersonAsyncTask(personDAO).execute(user);
//    }
//    public void update(UserEntities user) {
//        new UpdatePersonAsyncTask(personDAO).execute(user);
//    }
//    private static class InsertPersonAsyncTask extends AsyncTask<UserEntities, Void, Void> {
//        private UserDao personDAO;
//
//        private InsertPersonAsyncTask(UserDao personDAO) {
//            this.personDAO = personDAO;
//        }
//
//        @Override
//        protected Void doInBackground(UserEntities... users) {
//            personDAO.addPerson(users[0]);
//            return null;
//        }
//    }
//    private static class UpdatePersonAsyncTask extends AsyncTask<UserEntities, Void, Void> {
//        private UserDao personDAO;
//
//        private UpdatePersonAsyncTask(UserDao personDAO) {
//            this.personDAO = personDAO;
//        }
//
//        @Override
//        protected Void doInBackground(UserEntities... users) {
//            personDAO.updatePerson(users[0]);
//            return null;
//        }
//    }
//    public LiveData<UserEntities> getPersonById(int personId) {
//        return personDAO.getPersonById(personId);
//    }
//


}
