package com.example.proiect_real.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "class_table")
public class ClassEntity{
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    private String className;
    private String classTeacher;
    private String teacherMail;
    private String classDescription;

    public ClassEntity(String className, String classTeacher, String teacherMail,String classDescription) {
        this.className = className;
        this.classTeacher = classTeacher;
        this.teacherMail = teacherMail;
        this.classDescription = classDescription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassTeacher() {
        return classTeacher;
    }

    public void setClassTeacher(String classTeacher) {
        this.classTeacher = classTeacher;
    }

    public String getTeacherMail() {
        return teacherMail;
    }

    public void setTeacherMail(String teacherMail) {
        this.teacherMail = teacherMail;
    }

    public String getClassDescription() {
        return classDescription;
    }

    public void setClassDescription(String classDescription) {
        this.classDescription = classDescription;
    }
}
