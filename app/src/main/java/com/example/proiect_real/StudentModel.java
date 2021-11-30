package com.example.proiect_real;


import com.github.javafaker.Bool;

import java.io.Serializable;

public class StudentModel implements Serializable {
    private String firstName;
    private String lastName;
    private String birthday;
    private Double height;
    private Integer weight;
    private String email;
    private String address;
    private String parentFullName;
    private String parentEmail;
    private String parentPhone;
    private Boolean sex;

    public StudentModel(String firstName, String lastName, String birthday, Double height, Integer weight,
                        String email, String address, String parentFullName, String parentEmail, String parentPhone, Boolean sex) {
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

    public Boolean getSex() {
        return sex;
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
}

