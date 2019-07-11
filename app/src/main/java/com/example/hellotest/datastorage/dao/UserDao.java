package com.example.hellotest.datastorage.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.hellotest.datastorage.entity.User;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface UserDao {

    @Query("select * from users")
    List<User> getAll();

    @Query("select * from users")
    Flowable<List<User>> getAllAsync();

    @Query("select * from users where name = :name")
    User getUserByName(String name);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(User user);

    @Delete
    int delete(User user);

    @Update
    int updateUser(User user);

    @Update
    int updateUser(List<User> users);
}
