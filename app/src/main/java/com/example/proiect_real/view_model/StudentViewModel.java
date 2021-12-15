package com.example.proiect_real.view_model;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.proiect_real.entity.StudentEntity;
import com.example.proiect_real.repository.StudentRepository;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class StudentViewModel extends AndroidViewModel {
    private StudentRepository studentRepository;
    private LiveData<List<StudentEntity>> allStudents;

    public StudentViewModel(Application application){
        super(application);
        studentRepository = new StudentRepository(application);
        allStudents = studentRepository.getAllStudents();
    }

    public LiveData<List<StudentEntity>> getAllStudents() {
        return allStudents;
    }

    public void insert(StudentEntity studentEntity) {
        studentRepository.insert(studentEntity);
    }

    public void deleteAllStudents(){studentRepository.deleteAll();}

    public void deleteStudent(StudentEntity studentEntity) {studentRepository.deleteStudent(studentEntity);}

    public StudentEntity getStudentById(int id) throws ExecutionException, InterruptedException {return studentRepository.getStudentById(id);};
}
