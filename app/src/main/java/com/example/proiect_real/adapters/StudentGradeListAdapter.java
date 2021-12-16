package com.example.proiect_real.adapters;

import static android.app.Activity.RESULT_OK;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.proiect_real.OnIntentReceived;
import com.example.proiect_real.activities.LogInActivity;
import com.example.proiect_real.activities.NewGradesActivity;
import com.example.proiect_real.activities.NewStudentActivity;
import com.example.proiect_real.entity.StudentEntity;
import com.example.proiect_real.entity.StudentGradesEntity;
import com.example.proiect_real.models.StudentGradesModel;
import com.example.proiect_real.models.StudentModel;
import com.example.proiect_real.view_holder.StudentGradeViewHolder;
import com.example.proiect_real.view_model.GradesViewModel;
import com.example.proiect_real.view_model.StudentViewModel;
import com.example.proiect_real.view_model.UserViewModel;

import java.util.concurrent.ExecutionException;

public class StudentGradeListAdapter extends ListAdapter<StudentGradesEntity, StudentGradeViewHolder> {
    Context context;
    GradesViewModel gradesViewModel;
    StudentViewModel studentViewModel;
    StudentEntity studentEntity;
    public static final int NEW_GRADE_ACTIVITY_REQUEST_CODE = 4;
    private final String TAG = StudentGradeListAdapter.class.getSimpleName();

    public StudentGradeListAdapter(Context ctx, @NonNull DiffUtil.ItemCallback<StudentGradesEntity> diffCallBack, GradesViewModel gm) {

        super(diffCallBack);
        this.context = ctx;
        this.gradesViewModel = gm;
    }

    @NonNull
    @Override
    public StudentGradeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return StudentGradeViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentGradeViewHolder holder, int position) {
        StudentGradesEntity current = getItem(position);
        studentViewModel = new ViewModelProvider((ViewModelStoreOwner) context).get(StudentViewModel.class);
        try {
            studentEntity = studentViewModel.getStudentById(current.getStudentId());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        holder.bind(studentEntity.getFirstName() + " " + studentEntity.getLastName(), current.getSubject1_grades_json(), current.getSubject2_grades_json(), current.getSubject3_grades_json(), current.getSubject4_grades_json(), current.getSubject5_grades_json());

        holder.addGradeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NewGradesActivity.class);
                intent.putExtra("test",current);
                ((Activity) context).startActivityForResult(intent, NEW_GRADE_ACTIVITY_REQUEST_CODE);
            }
        });

        holder.deleteGradeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gradesViewModel.deleteGrade(current);
            }
        });
    }

    public static class GradeDiff extends DiffUtil.ItemCallback<StudentGradesEntity> {
        @Override
        public boolean areItemsTheSame(@NonNull StudentGradesEntity oldItem, @NonNull StudentGradesEntity newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull StudentGradesEntity oldItem, @NonNull StudentGradesEntity newItem) {
            return oldItem.getStudentId() == newItem.getStudentId();
        }
    }
}
