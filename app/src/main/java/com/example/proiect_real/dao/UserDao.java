package com.example.proiect_real.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.proiect_real.entity.StudentEntity;
import com.example.proiect_real.entity.UserEntity;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user_table where email= :mail and password= :password")
    UserEntity getUser(String mail, String password);

    @Insert
    void insert(UserEntity userEntity);

    @Query("Select * from user_table")
    LiveData<List<UserEntity>> getAllUsers();

    @Update
    void update(UserEntity userEntity);

    @Delete
    void delete(UserEntity userEntity);

}
