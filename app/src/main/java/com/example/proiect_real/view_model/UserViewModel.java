package com.example.proiect_real.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.proiect_real.entity.StudentEntity;
import com.example.proiect_real.entity.UserEntity;
import com.example.proiect_real.repository.StudentRepository;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class UserViewModel extends AndroidViewModel {
    private StudentRepository studentRepository;
    private LiveData<List<UserEntity>> userEntityList;

    public UserViewModel(@NonNull Application application) {
        super(application);
        studentRepository = new StudentRepository(application);
        this.userEntityList = studentRepository.getAllUsers();
    }

    public void insert(UserEntity userEntity) {
        studentRepository.insertUser(userEntity);
    }

    public LiveData<List<UserEntity>> getAllUsers() {
        return userEntityList;
    }

    public UserEntity getUser(String mail, String password) throws ExecutionException, InterruptedException { return studentRepository.getUser(mail, password);}

    public boolean checkValidLogin(String mail, String password){
        return studentRepository.isValidAccount(mail,password);
    }
}
