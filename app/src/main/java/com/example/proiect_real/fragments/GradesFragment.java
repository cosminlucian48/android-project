package com.example.proiect_real.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proiect_real.R;
import com.example.proiect_real.activities.MainScreenActivity;
import com.example.proiect_real.activities.RegisterActivity;
import com.example.proiect_real.adapters.StudentGradeListAdapter;
import com.example.proiect_real.adapters.StudentListAdapter;
import com.example.proiect_real.data.DataConverter;
import com.example.proiect_real.entity.StudentEntity;
import com.example.proiect_real.entity.StudentGradesEntity;
import com.example.proiect_real.view_model.ClassViewModel;
import com.example.proiect_real.view_model.GradesViewModel;
import com.example.proiect_real.view_model.StudentViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class GradesFragment extends Fragment {
    private final String TAG = GradesFragment.class.getSimpleName();
    private GradesViewModel gradesViewModel;
    private StudentViewModel studentViewModel;
    public RecyclerView recyclerView;
    public StudentGradeListAdapter adapter;
    ActivityResultLauncher<Intent> startActivityForResult;

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_grades, container, false);


//        List grades1 =  new ArrayList<>();
//        grades1.add(12);
//        grades1.add(13);
//        String stringGrades1 = DataConverter.fromListToJson(grades1);
//
//        List grades2 =  new ArrayList<>();
//        grades2.add(12);
//        grades2.add(13);
//        String stringGrades2 = DataConverter.fromListToJson(grades2);
//
//        StudentGradesEntity studentGradesEntity = new StudentGradesEntity(1,stringGrades1,stringGrades1,stringGrades1,stringGrades1,stringGrades1);
//
//        Button button = view.findViewById(R.id.button1);
//        Log.d(TAG," ianinte on click");
//        button.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Log.d(TAG," in on click");
//                gradesViewModel.addGrade(studentGradesEntity);
//            }
//        });
//
//        List<StudentGradesEntity> gradess= new ArrayList<StudentGradesEntity>();
//        gradess = gradesViewModel.getAllGrades();
//
//        for(StudentGradesEntity studentGradesEntity1: gradess){
//            Log.d(TAG,"studentid " + studentGradesEntity1.getStudentId());
//            Log.d(TAG,"mat1 " + studentGradesEntity1.getSubject1_grades_json());
//            Log.d(TAG,"mat2 " + studentGradesEntity1.getSubject2_grades_json());
//            Log.d(TAG,"mat3 " + studentGradesEntity1.getSubject3_grades_json());
//            Log.d(TAG,"mat4 " + studentGradesEntity1.getSubject4_grades_json());
//            Log.d(TAG,"mat5 " + studentGradesEntity1.getSubject5_grades_json());
//        }

        recyclerView  = view.findViewById(R.id.gradesView);
        gradesViewModel = new ViewModelProvider(this).get(GradesViewModel.class);
        studentViewModel = new ViewModelProvider(this).get(StudentViewModel.class);

        adapter = new StudentGradeListAdapter(getActivity(),new StudentGradeListAdapter.GradeDiff(),gradesViewModel);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));


//        studentViewModel.deleteAllStudents();
        gradesViewModel.getAllGrades().observe(getViewLifecycleOwner(),students1 -> {
            adapter.submitList(students1);
        });

//        studentViewModel.getAllStudents().observe(getViewLifecycleOwner(),students ->{
//            List<StudentGradesEntity> grades= new ArrayList<>();

//            for(StudentEntity studentEntity: students){
//                gradesViewModel.addGrade(new StudentGradesEntity(
//                        studentEntity.getId(),
//                        "[]",
//                        "[]",
//                        "[]",
//                        "[]",
//                        "[]"
//                ));
//            }
//        });




        return view;
    }

}
