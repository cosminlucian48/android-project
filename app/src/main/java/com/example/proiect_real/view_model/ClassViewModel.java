package com.example.proiect_real.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.proiect_real.entity.ClassEntity;
import com.example.proiect_real.repository.StudentRepository;

import java.util.List;

public class ClassViewModel extends AndroidViewModel {
    private StudentRepository studentRepository;
    private List<ClassEntity> classEntityList;

    public ClassViewModel(@NonNull Application application) {
        super(application);
        studentRepository = new StudentRepository(application);
        this.classEntityList = studentRepository.getAllClasses();
    }

    public List<ClassEntity> getAllClasses() {
        return this.classEntityList;
    }

    public void insertClasses(List<ClassEntity> classEntityList){
        studentRepository.insertClasses(classEntityList);
    }
}
