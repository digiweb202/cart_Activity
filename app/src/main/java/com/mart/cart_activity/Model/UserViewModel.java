package com.mart.cart_activity.Model;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.mart.cart_activity.Database.Databases;
import com.mart.cart_activity.Entities.UserEntities;
import com.mart.cart_activity.Repository.UserRepository;

import java.util.List;

public class UserViewModel  extends AndroidViewModel {

    private static UserRepository repository;

    private LiveData<List<UserEntities>> allPersons;

    public UserViewModel(Application application) {
        super(application);
        Databases Mydatabase = Room.databaseBuilder(application, Databases.class, "Persondb").build();
        repository = new UserRepository(Mydatabase);
        allPersons = repository.getAllPersons();
    }

    public LiveData<List<UserEntities>> getAllPersons() {
        return allPersons;
    }

    public void insert(UserEntities user) {
        repository.insert(user);
    }

    public static void update(UserEntities user) {
        repository.update(user);
    }

    public static LiveData<UserEntities> getPersonById(int personId) {
        return repository.getPersonById(personId);
    }
}
