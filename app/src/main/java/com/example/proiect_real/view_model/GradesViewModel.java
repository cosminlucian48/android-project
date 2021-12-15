package com.example.proiect_real.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.proiect_real.entity.ClassEntity;
import com.example.proiect_real.entity.StudentEntity;
import com.example.proiect_real.entity.StudentGradesEntity;
import com.example.proiect_real.repository.StudentRepository;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class GradesViewModel extends AndroidViewModel {
    private StudentRepository studentRepository;
    private LiveData<List<StudentGradesEntity>> studentGradesEntities;

    public GradesViewModel(@NonNull Application application) {
        super(application);
        studentRepository = new StudentRepository(application);
        this.studentGradesEntities = studentRepository.getAllGrades();
    }

    public LiveData<List<StudentGradesEntity>> getAllGrades() {
        return this.studentGradesEntities;
    }

    public void addGrade(StudentGradesEntity studentGradesEntity) {
        studentRepository.addGrade(studentGradesEntity);
    }

    public void deleteGrade(StudentGradesEntity studentGradesEntity) {
        studentRepository.deleteGrade(studentGradesEntity);
    }

    public StudentGradesEntity getGradeByStudentId(int id) throws ExecutionException, InterruptedException {
        return studentRepository.getGradeByStudentId(id);
    }


    public void updateGrade(StudentGradesEntity studentGradesEntity) {
        studentRepository.updateGrades(studentGradesEntity);
    }
}
