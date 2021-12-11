package com.example.proiect_real.models;


import com.example.proiect_real.entity.StudentEntity;
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


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setParentFullName(String parentFullName) {
        this.parentFullName = parentFullName;
    }

    public void setParentEmail(String parentEmail) {
        this.parentEmail = parentEmail;
    }

    public void setParentPhone(String parentPhone) {
        this.parentPhone = parentPhone;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }


}

