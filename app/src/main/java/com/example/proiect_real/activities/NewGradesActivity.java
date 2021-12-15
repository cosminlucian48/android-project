package com.example.proiect_real.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.proiect_real.R;
import com.example.proiect_real.adapters.StudentGradeListAdapter;
import com.example.proiect_real.data.DataConverter;
import com.example.proiect_real.entity.StudentGradesEntity;
import com.example.proiect_real.models.StudentGradesModel;
import com.example.proiect_real.view_model.GradesViewModel;
import com.example.proiect_real.view_model.StudentViewModel;

import java.util.List;

public class NewGradesActivity extends AppCompatActivity {

    GradesViewModel gradesViewModel;
    private final String TAG = NewGradesActivity.class.getSimpleName();
    Button button;

    private EditText studentGrade1;
    private EditText studentGrade2;
    private EditText studentGrade3;
    private EditText studentGrade4;
    private EditText studentGrade5;

    String s1, s2, s3, s4, s5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_grades);
        button = findViewById(R.id.addNewGrades);
        gradesViewModel = new ViewModelProvider(this).get(GradesViewModel.class);
        StudentGradesEntity studentGradesEntity = (StudentGradesEntity) getIntent().getExtras().getSerializable("test");
//        studentGradesModel.setSubject2_grades_json("Ana");

        studentGrade1 = findViewById(R.id.mat1);
        studentGrade2 = findViewById(R.id.mat2);
        studentGrade3 = findViewById(R.id.mat3);
        studentGrade4 = findViewById(R.id.mat4);
        studentGrade5 = findViewById(R.id.mat5);


//        StudentGradesEntity updatedStudentGradesEntity
//        Log.d(TAG+"1",String.valueOf(studentGradesEntity.getStudentId()));
//        Log.d(TAG+"2",String.valueOf(studentGradesEntity.getSubject1_grades_json()));
//        Log.d(TAG+"3",String.valueOf(studentGradesEntity.getSubject2_grades_json()));
//        Log.d(TAG+"4",String.valueOf(studentGradesEntity.getSubject3_grades_json()));
//        Log.d(TAG+"5",String.valueOf(studentGradesEntity.getSubject4_grades_json()));
//        Log.d(TAG+"6",String.valueOf(studentGradesEntity.getSubject5_grades_json()));


//        Log.d(TAG,String.valueOf(studentGradesModel.getStudentId()));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent replyIntent = new Intent();
                Bundle replyBundle = new Bundle();
//                replyBundle.putSerializable(EXTRA_REPLY,studentModel);
                replyIntent.putExtra("String", "iar");
                setResult(RESULT_OK, replyIntent);
                Log.d(TAG, "Mi am facut treaba in new grades");
                if (studentGrade1.getText().toString().isEmpty()) {
                    s1 = studentGradesEntity.getSubject1_grades_json();
                } else {
                    s1 = addGradeToJson(studentGrade1.getText().toString(),studentGradesEntity.getSubject1_grades_json());
                }
                if (studentGrade2.getText().toString().isEmpty()) {
                    s2 = studentGradesEntity.getSubject2_grades_json();
                } else {
                    s2 = addGradeToJson(studentGrade2.getText().toString(),studentGradesEntity.getSubject2_grades_json());

                }
                if (studentGrade3.getText().toString().isEmpty()) {
                    s3 = studentGradesEntity.getSubject3_grades_json();
                } else {
                    s3 = addGradeToJson(studentGrade3.getText().toString(),studentGradesEntity.getSubject3_grades_json());
                }
                if (studentGrade4.getText().toString().isEmpty()) {
                    s4 = studentGradesEntity.getSubject4_grades_json();
                } else {
                    s4 = addGradeToJson(studentGrade4.getText().toString(),studentGradesEntity.getSubject4_grades_json());
                }
                if (studentGrade5.getText().toString().isEmpty()) {
                    s5 = studentGradesEntity.getSubject5_grades_json();
                } else {
                    s5 = addGradeToJson(studentGrade5.getText().toString(),studentGradesEntity.getSubject5_grades_json());
                }

                StudentGradesEntity updatedStudentGradesEntity = new StudentGradesEntity(studentGradesEntity.getStudentId(),s1,s2,s3,s4,s5);

                updatedStudentGradesEntity.setId(studentGradesEntity.getId());
                gradesViewModel.updateGrade(updatedStudentGradesEntity);
                finish();
            }
        });

    }

    private String addGradeToJson(String grade, String json){
        List<Integer> aux = DataConverter.fromJsonToList(json);
        aux.add(Integer.valueOf(grade));
        return DataConverter.fromListToJson(aux);
    }
}