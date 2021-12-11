package com.example.proiect_real.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.proiect_real.entity.StudentEntity;

import java.util.List;

@Dao
public abstract class StudentDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public abstract void insert(StudentEntity studentEntity);

    @Query("Delete from student_table")
    public abstract void deleteAll();

    @Query("Select * from student_table")
    public abstract LiveData<List<StudentEntity>> getAllStudents();

    @Update
    public abstract void updateStudent(StudentEntity studentEntity);

    @Delete
    public abstract void delete(StudentEntity studentEntity);
}
