package com.mart.cart_activity.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.mart.cart_activity.Dao.UserDao;
import com.mart.cart_activity.Entities.UserEntities;

@Database(entities = {UserEntities.class}, version = 1)
public abstract class Databases extends RoomDatabase {
    //    Now creating An abstract class for our database
    // that will be  implementation related in database entities related work
    public abstract UserDao getPersonDAO();



}
