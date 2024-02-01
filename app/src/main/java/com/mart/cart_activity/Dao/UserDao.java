package com.mart.cart_activity.Dao;


import androidx.lifecycle.LiveData;
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

    @Query("SELECT * FROM userlogin")
    LiveData<List<UserEntities>> getAllPerson(); // Change the return type to LiveData

    @Query("SELECT * FROM userlogin WHERE person_id = :person_id")
    UserEntities getPerson(int person_id);
    @Query("SELECT * FROM userlogin WHERE person_id = :personId")
    LiveData<UserEntities> getPersonById(int personId);

    @Query("DELETE FROM userlogin WHERE person_id = :personId")
    void deletePersonById(int personId);

    @Query("SELECT * FROM userlogin WHERE person_id = :userId")
    UserEntities getUserById(long userId);

}
