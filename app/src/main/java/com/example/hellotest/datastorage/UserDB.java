package com.example.hellotest.datastorage;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.support.annotation.NonNull;

import com.example.hellotest.datastorage.dao.UserDao;
import com.example.hellotest.datastorage.entity.User;

@Database(entities = User.class, version = 3, exportSchema = false)
public abstract class UserDB extends RoomDatabase {
    private static UserDB INSTANCE;
    private static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE users ADD COLUMN email VARCHAR(50)");
        }
    };

    public static UserDB getINSTANCE(Context context){


        if(INSTANCE == null){
            //INSTANCE = Room.databaseBuilder(context.getApplicationContext(), UserDB.class, "user.db").allowMainThreadQueries().fallbackToDestructiveMigration().build();
            INSTANCE = Room.databaseBuilder(context, UserDB.class, "user.db").allowMainThreadQueries().addMigrations(MIGRATION_2_3).build();
        }
        return INSTANCE;
    }

    public abstract UserDao getUserDao();

    public static void onDestory(){
        INSTANCE = null;
    }
}
