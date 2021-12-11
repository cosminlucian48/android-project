package com.example.proiect_real.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.proiect_real.dao.StudentDao;
import com.example.proiect_real.database.ClassroomRoomDatabase;
import com.example.proiect_real.entity.StudentEntity;

import java.util.List;

public class StudentRepository {

    private StudentDao studentDao;
    private LiveData<List<StudentEntity>> allStudents;

    public StudentRepository(Application application){
        ClassroomRoomDatabase db = ClassroomRoomDatabase.getDatabase(application);
        studentDao = db.studentDao();
        allStudents = studentDao.getAllStudents();
    }

    public LiveData<List<StudentEntity>> getAllStudents() {
        return allStudents;
    }

    public void insert(StudentEntity studentEntity){
        ClassroomRoomDatabase.databaseWriteExecutor.execute(() ->{
            studentDao.insert(studentEntity);
        });
    }
    public void deleteAll(){
        new deleteAllStudentsAsyncTask(studentDao).execute();
    }

//    public void deleteStudent(StudentEntity studentEntity){studentDao.delete(studentEntity);}

    public void deleteStudent(StudentEntity studentEntity){new deleteStudentAsyncTask(studentDao,studentEntity).execute();}

    private static class deleteAllStudentsAsyncTask extends AsyncTask<Void, Void, Void> {

        private StudentDao studentDao;

        deleteAllStudentsAsyncTask(StudentDao dao) {
            this.studentDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            studentDao.deleteAll();
            return null;
        }
    }

    private static class deleteStudentAsyncTask extends  AsyncTask<Void,Void,Void> {
        private StudentDao studentDao;
        private StudentEntity studentEntity;

        public deleteStudentAsyncTask(StudentDao studentDao, StudentEntity studentEntity) {
            this.studentDao = studentDao;
            this.studentEntity = studentEntity;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            studentDao.delete(studentEntity);
            return null;
        }
    }

}
