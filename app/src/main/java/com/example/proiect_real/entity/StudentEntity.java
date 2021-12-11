package com.example.proiect_real.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "student_table")
public class StudentEntity {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "studentId")
    public int id;

    @ColumnInfo(name = "firstName")
    public String firstName;

    @ColumnInfo(name = "lastName")
    public String lastName;

    @ColumnInfo(name = "birthday")
    public String birthday;

    @ColumnInfo(name = "height")
    public Double height;

    @ColumnInfo(name = "weight")
    public Integer weight;

    @ColumnInfo(name = "email")
    public String email;

    @ColumnInfo(name = "address")
    public String address;

    @ColumnInfo(name = "parentFullName")
    public String parentFullName;

    @ColumnInfo(name = "parentEmail")
    public String parentEmail;

    @ColumnInfo(name = "parentPhone")
    public String parentPhone;

    @ColumnInfo(name = "sex")
    public Boolean sex;

    public StudentEntity(@NonNull String firstName, String lastName, String birthday,
                         Double height, Integer weight, String email, String address,
                         String parentFullName, String parentEmail, String parentPhone, Boolean sex) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.height = height;
        this.weight = weight;
        this.email = email;
        this.address = address;
        this.parentFullName = parentFullName;
        this.parentEmail = parentEmail;
        this.parentPhone = parentPhone;
        this.sex = sex;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBirthday() {
        return birthday;
    }

    public Double getHeight() {
        return height;
    }

    public Integer getWeight() {
        return weight;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getParentFullName() {
        return parentFullName;
    }

    public String getParentEmail() {
        return parentEmail;
    }

    public String getParentPhone() {
        return parentPhone;
    }

    public Boolean getSex() {
        return sex;
    }
}
