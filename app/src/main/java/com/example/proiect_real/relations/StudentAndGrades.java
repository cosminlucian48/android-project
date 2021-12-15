package com.example.proiect_real.relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.proiect_real.entity.StudentEntity;
import com.example.proiect_real.entity.StudentGradesEntity;
import com.example.proiect_real.entity.UserEntity;

public class StudentAndGrades {
    @Embedded public StudentEntity studentEntity;
    @Relation(
            parentColumn = "studentId",
            entityColumn = "studentId"
    )
    public StudentGradesEntity studentGradesEntity;
}
