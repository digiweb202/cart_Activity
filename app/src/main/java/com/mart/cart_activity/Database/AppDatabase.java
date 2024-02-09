package com.mart.cart_activity.Database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.mart.cart_activity.Dao.ProductDao;
import com.mart.cart_activity.Dao.UserDao;
import com.mart.cart_activity.Dao.UserSignupDao;
import com.mart.cart_activity.Entities.ProductListEntities;
import com.mart.cart_activity.Entities.UserSignupEntities;

@Database(entities = {UserSignupEntities.class, ProductListEntities.class}, version = 5, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instances;

    public abstract UserSignupDao userSignupDao();

    public abstract ProductDao productDao();
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
    private static final Migration MIGRATION_3_4 = new Migration(3, 4) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            // Migration logic from version 3 to 4
            // For example, you can run SQL queries to modify the schema
        }
    };

    // Add other migrations as needed

//    public static synchronized AppDatabase getInstance(Context context) {
//        if (instances == null) {
//            instances = Room.databaseBuilder(context.getApplicationContext(),
//                            AppDatabase.class, "Persondb")
//                    .addMigrations(MIGRATION_3_4)
//                    // Add other migrations as needed
//                    .build();
//        }
//        return instances;
//    }
}