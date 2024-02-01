package com.mart.cart_activity.Databaseinitializers;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.mart.cart_activity.Database.Databases;

public class DatabaseInitializers {


    private static final String DATABASE_NAME = "Persondb";
    private static Databases instance;

    public static synchronized Databases getInstance(Context context) {
        if (instance == null) {
            instance = buildDatabase(context);
        }
        return instance;
    }

    private static Databases buildDatabase(final Context context) {
        return Room.databaseBuilder(
                        context.getApplicationContext(),
                        Databases.class,
                        DATABASE_NAME
                )
                .addCallback(new RoomDatabase.Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        // You can perform initialization tasks here when the database is created.
                    }

                    @Override
                    public void onDestructiveMigration(@NonNull SupportSQLiteDatabase db) {
                        super.onDestructiveMigration(db);
                        // Handle destructive migration (e.g., drop and recreate tables) here if needed.
                    }
                })
                .build();
    }
}
