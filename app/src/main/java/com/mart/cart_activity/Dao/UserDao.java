package com.mart.cart_activity.Dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mart.cart_activity.Entities.UserEntities;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    public void addPerson(UserEntities user);
    @Update
    public void updatePerson(UserEntities user);
    @Delete
    public void deletePerson(UserEntities user);

    @Query("select * from userlogin")
    public List<UserEntities> getAllPerson();
    @Query("select * from userlogin where person_id ==:person_id")
    public UserEntities getPerson(int person_id);
}
