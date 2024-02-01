package com.mart.cart_activity.Entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
@Entity(tableName = "userprofile")
public class UserDataEntities {

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    long id;

    @ColumnInfo(name = "username")
    String name;

    @ColumnInfo(name = "password")
    String age;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Ignore
    public UserDataEntities(long id, String name, String age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public UserDataEntities(String name, String age) {
        this.name = name;
        this.age = age;
    }
}
