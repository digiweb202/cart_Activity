package com.mart.cart_activity.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.mart.cart_activity.Dao.UserDao;
import com.mart.cart_activity.Dao.UserSignupDao;
import com.mart.cart_activity.Entities.UserSignupEntities;

@Database(entities = {UserSignupEntities.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instances;

    public abstract UserSignupDao userSignupDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (instances == null) {
            instances = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "Persondb")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instances;
    }
    public abstract UserDao getPersonDAO();
    // Other DAOs can be added here

    // ...
}