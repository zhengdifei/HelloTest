package com.example.hellotest.datastorage.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "users", indices = {@Index("name")})
public class User {

    @PrimaryKey(autoGenerate = true)
    public int id;
    @NonNull
    public String name;
    @ColumnInfo(name = "address")
    public String addr;
    public String phone;
    @Ignore
    public String nick;

    public String email;

    public User(int id){
        this.id = id;
    }

    @Ignore
    public User(String name, String addr, String phone){
        this.name = name;
        this.addr = addr;
        this.phone = phone;
        this.email = "email@qq.com";
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", addr='" + addr + '\'' +
                ", phone='" + phone + '\'' +
                ", nick='" + nick + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
