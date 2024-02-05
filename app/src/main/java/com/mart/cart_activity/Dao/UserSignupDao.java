package com.mart.cart_activity.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mart.cart_activity.Entities.UserSignupEntities;

import java.util.List;

@Dao
public interface UserSignupDao {
    @Insert
    void insert(UserSignupEntities user);

    @Query("SELECT * FROM userSignup WHERE username = :username AND password = :password")
    UserSignupEntities loginUser(String username, String password);

    @Query("SELECT * FROM userSignup")
    LiveData<List<UserSignupEntities>> getAllUsers(); // Add this line

    @Query("SELECT * FROM userSignup WHERE id = :userId")
    LiveData<UserSignupEntities> getUserById(int userId);
    @Update
    void updateUser(UserSignupEntities user);





}
