package com.mart.cart_activity.Model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mart.cart_activity.Dao.UserSignupDao;
import com.mart.cart_activity.Database.AppDatabase;
import com.mart.cart_activity.Entities.UserSignupEntities;
import com.mart.cart_activity.Repository.UserSignupRepository;

public class UserViewModel extends AndroidViewModel {
    private UserSignupRepository userSignupRepository;
    private UserSignupDao userSignupDao;

    public UserViewModel(@NonNull Application application) {
        super(application);

        AppDatabase database = AppDatabase.getInstance(application);
        userSignupDao = database.userSignupDao();
        userSignupRepository = new UserSignupRepository(application); // Initialize the repository
    }

    public LiveData<UserSignupEntities> getUserById(int userId) {
        return userSignupDao.getUserById(userId);
    }

    public void updateUser(UserSignupEntities user) {
        userSignupRepository.updateUser(user);
    }
}
