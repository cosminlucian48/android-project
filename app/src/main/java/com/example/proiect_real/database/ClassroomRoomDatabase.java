package com.example.proiect_real.database;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.proiect_real.dao.ClassDao;
import com.example.proiect_real.dao.StudentDao;
import com.example.proiect_real.dao.StudentGradesDao;
import com.example.proiect_real.dao.UserDao;
import com.example.proiect_real.entity.ClassEntity;
import com.example.proiect_real.entity.StudentEntity;
import com.example.proiect_real.entity.StudentGradesEntity;
import com.example.proiect_real.entity.UserEntity;
import com.example.proiect_real.fragments.StudentDialogFragment;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {StudentEntity.class, UserEntity.class, ClassEntity.class, StudentGradesEntity.class}, version = 4, exportSchema = false)
public abstract class ClassroomRoomDatabase extends RoomDatabase {

    public abstract StudentDao studentDao();
    public abstract UserDao userDao();
    public abstract ClassDao classDao();
    public abstract StudentGradesDao studentGradesDao();

    private static final String DB_name = "student_table";
    private static final String TAG = ClassroomRoomDatabase.class.getSimpleName();
    private static volatile ClassroomRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;

    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    private static final Migration MIGRATION_1_2 = new Migration(1,2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE `user_table` (`id` INTEGER PRIMARY KEY NOT NULL," +
                    "`userName` TEXT, `password` TEXT, `email` TEXT, `accountType` TEXT )");
        }
    };

    private static final Migration MIGRATION_2_3 = new Migration(2,3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE `class_table` (`id` INTEGER PRIMARY KEY NOT NULL," +
                    "`className` TEXT, `classTeacher` TEXT, `teacherMail` TEXT, `classDescription` TEXT )");
        }
    };

    private static final Migration MIGRATION_3_4 = new Migration(3,4) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE `grades_table` (`id` INTEGER PRIMARY KEY NOT NULL," +
                    "`studentId` INTEGER NOT NULL, `subject1_grades_json` TEXT, `subject2_grades_json` TEXT, `subject3_grades_json` TEXT, " +
                    "`subject4_grades_json` TEXT, `subject5_grades_json` TEXT   )");
        }
    };
//    private int id;
//    private int studentId;
//    private String subject1_grades_json;
//    private String subject2_grades_json;
//    private String subject3_grades_json;
//    private String subject4_grades_json;
//    private String subject5_grades_json;


    public static ClassroomRoomDatabase getDatabase(final Context context) {
//        Log.d(TAG,"aici e schema");

        if (INSTANCE == null) {
            synchronized (ClassroomRoomDatabase.class) {
                if(INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ClassroomRoomDatabase.class, DB_name)
                            .addMigrations(MIGRATION_1_2)
                            .addMigrations(MIGRATION_2_3)
                            .addMigrations(MIGRATION_3_4)
//                            .allowMainThreadQueries()
                            .addCallback(classroomRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static ClassroomRoomDatabase.Callback classroomRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            Log.d(TAG,"aici ar trebui sa fie schema");
            // If you want to keep data through app restarts,
            // comment out the following block
//            databaseWriteExecutor.execute(() -> {
//                // Populate the database in the background.
//                // If you want to start with more words, just add them.
//                StudentDao dao = INSTANCE.studentDao();
//                LiveData<List<StudentEntity>> studentEntityList = dao.getAllStudents();
//
//                Log.d(TAG, String.valueOf(studentEntityList));
//                dao.deleteAll();
//
//                studentEntityList = dao.getAllStudents();
//
//                Log.d(TAG, String.valueOf(studentEntityList));
//
//
//
////                Word word = new Word("Hello");
////                dao.insert(word);
////                word = new Word("World");
////                dao.insert(word);
//            });
        }
    };

}
