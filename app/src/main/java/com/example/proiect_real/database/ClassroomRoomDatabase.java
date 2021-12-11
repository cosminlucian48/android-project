package com.example.proiect_real.database;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.proiect_real.dao.StudentDao;
import com.example.proiect_real.entity.StudentEntity;
import com.example.proiect_real.fragments.StudentDialogFragment;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {StudentEntity.class}, version = 1, exportSchema = false)
public abstract class ClassroomRoomDatabase extends RoomDatabase {

    public abstract StudentDao studentDao();

    private static final String DB_name = "student_table";
    private static final String TAG = ClassroomRoomDatabase.class.getSimpleName();
    private static volatile ClassroomRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;

    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static ClassroomRoomDatabase getDatabase(final Context context) {
//        Log.d(TAG,"aici e schema");
        if (INSTANCE == null) {
            synchronized (ClassroomRoomDatabase.class) {
                if(INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ClassroomRoomDatabase.class, DB_name)
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
