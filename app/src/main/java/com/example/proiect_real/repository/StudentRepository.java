package com.example.proiect_real.repository;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.proiect_real.adapters.StudentGradeListAdapter;
import com.example.proiect_real.dao.ClassDao;
import com.example.proiect_real.dao.StudentDao;
import com.example.proiect_real.dao.StudentGradesDao;
import com.example.proiect_real.dao.UserDao;
import com.example.proiect_real.database.ClassroomRoomDatabase;
import com.example.proiect_real.entity.ClassEntity;
import com.example.proiect_real.entity.StudentEntity;
import com.example.proiect_real.entity.StudentGradesEntity;
import com.example.proiect_real.entity.UserEntity;
import com.example.proiect_real.models.StudentGradesModel;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class StudentRepository {

    private StudentDao studentDao;
    private UserDao userDao;
    private ClassDao classDao;
    private StudentGradesDao studentGradesDao;
    private LiveData<List<StudentEntity>> allStudents;
    private List<ClassEntity> allClasses;
    private LiveData<List<StudentGradesEntity>> allGrades;
    private LiveData<List<UserEntity>> allUsers;



    public StudentRepository(Application application) {
        ClassroomRoomDatabase db = ClassroomRoomDatabase.getDatabase(application);
        studentDao = db.studentDao();
        userDao = db.userDao();
        classDao = db.classDao();
        studentGradesDao = db.studentGradesDao();

        allStudents = studentDao.getAllStudents();
        allUsers = userDao.getAllUsers();
//        allClasses = classDao.getAllClasses();
        allGrades = studentGradesDao.getAllGrades();
    }

    public LiveData<List<StudentEntity>> getAllStudents() {
        return allStudents;
    }

//    public LiveData<List<UserEntity>> getAllUsers() {
//        return allUsers;
//    }
public List<UserEntity> getAllUsers() throws ExecutionException, InterruptedException {
    return new GetUsersAsyncTask(userDao).execute().get();
}
//    public UserEntity getUser(String mail, String password) {
//        return userDao.getUser(mail, password);
//    }

    public UserEntity getUser(String mail, String password) throws ExecutionException, InterruptedException {

        Callable<UserEntity> callable = new Callable<UserEntity>() {
            @Override
            public UserEntity call() throws Exception {
                return userDao.getUser(mail, password);
            }
        };

        Future<UserEntity> future = Executors.newSingleThreadExecutor().submit(callable);

        return future.get();
    }

    public boolean isValidAccount(String mail, String password) throws ExecutionException, InterruptedException {
        UserEntity userEntity = new GetUserByEmailAndPAsync(userDao,mail,password).execute().get();
        if(userEntity==null){
            return false;
        }
        return true;

//        ClassroomRoomDatabase.databaseWriteExecutor.execute(() -> {
//            userEntity[0] = userDao.getUser(mail, password);
//            Log.d("repoo",userEntity[0].getPassword());
//        });
//
//        if (userEntity[0] == null) {
//            return false;
//        }
//        Log.d("AICI pls",String.valueOf(userEntity[0].getPassword()));
//        return userEntity[0].getPassword().equals(password);

    }

//    public StudentGradesEntity getGradeByStudentId(int id) throws ExecutionException, InterruptedException{
//        Callable<StudentGradesEntity> callable = new Callable<StudentGradesEntity>() {
//            @Override
//            public StudentGradesEntity call() throws Exception {
//                return studentGradesDao.getGradeByStudentId(id);
//            }
//        };
//
//        Future<StudentGradesEntity> future = Executors.newSingleThreadExecutor().submit(callable);
//
//        return future.get();
//    }

    public void insert(StudentEntity studentEntity) {
        ClassroomRoomDatabase.databaseWriteExecutor.execute(() -> {
            studentDao.insert(studentEntity);
        });
    }

    public void insertUser(UserEntity userEntity) {
        ClassroomRoomDatabase.databaseWriteExecutor.execute(() -> {
            userDao.insert(userEntity);
        });
    }

    public void deleteAll() {
        new deleteAllStudentsAsyncTask(studentDao).execute();
    }

//    public void deleteStudent(StudentEntity studentEntity){studentDao.delete(studentEntity);}

    public void deleteStudent(StudentEntity studentEntity) {
        new deleteStudentAsyncTask(studentDao, studentEntity).execute();
    }

    public List<ClassEntity> getAllClasses() throws ExecutionException, InterruptedException {
        return new GetClassesAsyncTask(classDao).execute().get();
    }

    public void insertClasses(List<ClassEntity> classEntityList) {
        ClassroomRoomDatabase.databaseWriteExecutor.execute(() -> {
            classDao.insertClasses(classEntityList);
        });
    }


    public void addGrade(StudentGradesEntity studentGradesEntity) {
        ClassroomRoomDatabase.databaseWriteExecutor.execute(() -> {
            studentGradesDao.addGrade(studentGradesEntity);
        });
    }

    public LiveData<List<StudentGradesEntity>> getAllGrades() {
        return allGrades;
    }

    public void deleteAllGrades() {
        new deleteAllGradesAsyncTask(studentGradesDao).execute();
    }

    public void deleteGrade(StudentGradesEntity studentGradesEntity) {
        new deleteGradeAsyncTask(studentGradesDao, studentGradesEntity).execute();
    }

//    public void updateGrades(StudentGradesEntity studentGradesEntity){
//        studentGradesDao.update(studentGradesEntity);
//    }
    public void updateGrades(StudentGradesEntity studentGradesEntity) {
        new UpdateCourseAsyncTask(studentGradesDao).execute(studentGradesEntity);
    }

    public StudentGradesEntity getGradeByStudentId(int id) throws ExecutionException, InterruptedException{
        Callable<StudentGradesEntity> callable = new Callable<StudentGradesEntity>() {
            @Override
            public StudentGradesEntity call() throws Exception {
                return studentGradesDao.getGradeByStudentId(id);
            }
        };

        Future<StudentGradesEntity> future = Executors.newSingleThreadExecutor().submit(callable);

        return future.get();
    }
//    public UserEntity getUser(String mail, String password) throws ExecutionException, InterruptedException {
//
//        Callable<UserEntity> callable = new Callable<UserEntity>() {
//            @Override
//            public UserEntity call() throws Exception {
//                return userDao.getUser(mail, password);
//            }
//        };
//
//        Future<UserEntity> future = Executors.newSingleThreadExecutor().submit(callable);
//
//        return future.get();
//    }
    private static class UpdateCourseAsyncTask extends AsyncTask<StudentGradesEntity, Void, Void> {
        private StudentGradesDao dao;

        private UpdateCourseAsyncTask(StudentGradesDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(StudentGradesEntity... models) {
            // below line is use to update
            // our modal in dao.
            dao.update(models[0]);
            return null;
        }
    }

    private class GetUserByEmailAndPAsync extends AsyncTask<Void, Void,UserEntity>
    {
        private UserDao dao;
        String email;
        String password;

        public GetUserByEmailAndPAsync(UserDao dao,String email, String password) {
            this.dao = dao;
            this.email = email;
            this.password = password;
        }

        @Override
        protected UserEntity doInBackground(Void... url) {
            return dao.getUser(email,password);
        }
    }

    private class GetUsersAsyncTask extends AsyncTask<Void, Void,List<UserEntity>>
    {
        private UserDao dao;

        public GetUsersAsyncTask(UserDao dao) {
            this.dao = dao;
        }

        @Override
        protected List<UserEntity> doInBackground(Void... url) {
            return dao.getAllUsersList();
        }
    }

    private class GetClassesAsyncTask extends AsyncTask<Void, Void,List<ClassEntity>>
    {
        private ClassDao dao;

        public GetClassesAsyncTask(ClassDao dao) {
            this.dao = dao;
        }

        @Override
        protected List<ClassEntity> doInBackground(Void... url) {
            return dao.getAllClasses();
        }
    }

//    private static class updateHitAsyncTask extends AsyncTask<StudentGradesEntity,Void,Void>{
//
//        private StudentGradesDao studentGradesDao;
//
//        public updateHitAsyncTask(StudentGradesDao dao) {
//
//            studentGradesDao = dao;
//        }
//
//        @Override
//        protected Void doInBackground(StudentGradesEntity... studentGradesEntity) {
////            int studentId = studentGradesEntity[0].getStudentId();
//            String subject1_grades = studentGradesEntity[0].getSubject1_grades_json();
//            String subject2_grades = studentGradesEntity[0].getSubject2_grades_json();
//            String subject3_grades = studentGradesEntity[0].getSubject3_grades_json();
//            String subject4_grades = studentGradesEntity[0].getSubject4_grades_json();
//            String subject5_grades = studentGradesEntity[0].getSubject5_grades_json();
//
//            studentGradesDao.update(studentGradesEntity[0]);
//            return null;
//        }
//    }

    private static class deleteAllGradesAsyncTask extends AsyncTask<Void, Void, Void> {

        private StudentGradesDao studentGradesDao;

        deleteAllGradesAsyncTask(StudentGradesDao dao) {
            this.studentGradesDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            studentGradesDao.deleteAll();
            return null;
        }
    }

    private static class deleteGradeAsyncTask extends AsyncTask<Void, Void, Void> {
        private StudentGradesDao studentGradesDao;
        private StudentGradesEntity studentGradesEntity;
        public final String TAG = StudentRepository.class.getSimpleName();

        public deleteGradeAsyncTask(StudentGradesDao studentGradesDao, StudentGradesEntity studentGradesEntity) {
            this.studentGradesDao = studentGradesDao;
            this.studentGradesEntity = studentGradesEntity;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Log.d(TAG,"in do in background");
            studentGradesDao.deleteGrade(studentGradesEntity);
            return null;
        }
    }


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

    private static class deleteStudentAsyncTask extends AsyncTask<Void, Void, Void> {
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
