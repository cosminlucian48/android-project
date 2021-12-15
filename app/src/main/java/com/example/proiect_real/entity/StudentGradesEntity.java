package com.example.proiect_real.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "grades_table")
public class StudentGradesEntity implements Serializable{
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int studentId;
    private String subject1_grades_json;
    private String subject2_grades_json;
    private String subject3_grades_json;
    private String subject4_grades_json;
    private String subject5_grades_json;

    public StudentGradesEntity(int studentId, String subject1_grades_json, String subject2_grades_json, String subject3_grades_json, String subject4_grades_json, String subject5_grades_json) {
        this.studentId = studentId;
        this.subject1_grades_json = subject1_grades_json;
        this.subject2_grades_json = subject2_grades_json;
        this.subject3_grades_json = subject3_grades_json;
        this.subject4_grades_json = subject4_grades_json;
        this.subject5_grades_json = subject5_grades_json;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getSubject1_grades_json() {
        return subject1_grades_json;
    }

    public void setSubject1_grades_json(String subject1_grades_json) {
        this.subject1_grades_json = subject1_grades_json;
    }

    public String getSubject2_grades_json() {
        return subject2_grades_json;
    }

    public void setSubject2_grades_json(String subject2_grades_json) {
        this.subject2_grades_json = subject2_grades_json;
    }

    public String getSubject3_grades_json() {
        return subject3_grades_json;
    }

    public void setSubject3_grades_json(String subject3_grades_json) {
        this.subject3_grades_json = subject3_grades_json;
    }

    public String getSubject4_grades_json() {
        return subject4_grades_json;
    }

    public void setSubject4_grades_json(String subject4_grades_json) {
        this.subject4_grades_json = subject4_grades_json;
    }

    public String getSubject5_grades_json() {
        return subject5_grades_json;
    }

    public void setSubject5_grades_json(String subject5_grades_json) {
        this.subject5_grades_json = subject5_grades_json;
    }
}
