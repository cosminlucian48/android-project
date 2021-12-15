package com.example.proiect_real.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.proiect_real.entity.StudentGradesEntity;
import com.example.proiect_real.entity.UserEntity;
import com.example.proiect_real.relations.StudentAndGrades;

import java.util.List;

@Dao
public interface StudentGradesDao {

    @Insert
    void addGrade(StudentGradesEntity studentGradesEntity);

    @Query("Select * from grades_table")
    LiveData<List<StudentGradesEntity>> getAllGrades();

    @Transaction
    @Query("Select * from student_table")
    List<StudentAndGrades> getStudentAndGrades();

    @Query("Delete from grades_table")
    void deleteAll();

    @Update
    void update(StudentGradesEntity studentGradesEntity);

    @Delete
    void deleteGrade(StudentGradesEntity studentGradesEntity);

    @Query("Select * from grades_table where studentId=:id")
    StudentGradesEntity getGradeByStudentId(int id);
}
