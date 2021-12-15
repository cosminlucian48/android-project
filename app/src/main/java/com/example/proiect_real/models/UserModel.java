package com.example.proiect_real.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

public class UserModel implements Serializable {

    private String userName;
    private String password;
    private String email ;
    private String accountType; // professor or student

    public UserModel(String userName, String password, String email, String accountType) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.accountType = accountType;
    }

    public String getUserName() {
        return userName;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
