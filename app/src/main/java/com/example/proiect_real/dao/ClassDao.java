package com.example.proiect_real.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.proiect_real.entity.ClassEntity;
import com.example.proiect_real.entity.UserEntity;

import java.util.List;

@Dao
public interface ClassDao {
    @Query("Select * from class_table")
    List<ClassEntity> getAllClasses();

    @Insert
    void insertClasses(List<ClassEntity> classEntityList);
}
