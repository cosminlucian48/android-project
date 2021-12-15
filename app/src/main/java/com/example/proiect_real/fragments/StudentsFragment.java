package com.example.proiect_real.fragments;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proiect_real.OnIntentReceived;
import com.example.proiect_real.entity.StudentEntity;
import com.example.proiect_real.activities.NewStudentActivity;
import com.example.proiect_real.R;
import com.example.proiect_real.entity.StudentGradesEntity;
import com.example.proiect_real.models.StudentModel;
import com.example.proiect_real.adapters.StudentListAdapter;
import com.example.proiect_real.view_model.GradesViewModel;
import com.example.proiect_real.view_model.StudentViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class StudentsFragment extends Fragment {
    public RecyclerView recyclerView;
    List<StudentModel> studentList;
    //    StudentAdapter studentAdapter;
    List<StudentEntity> studentEntities;
    private static final String TAG = StudentsFragment.class.getSimpleName();
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
    public StudentViewModel studentViewModel;
    public GradesViewModel gradesViewModel;
    Set<Integer> oldGrades;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_students, container, false);
        recyclerView = view.findViewById(R.id.studentsRecyclerView);
        studentViewModel = new ViewModelProvider(this).get(StudentViewModel.class);
        gradesViewModel = new ViewModelProvider(this).get(GradesViewModel.class);
        final StudentListAdapter adapter = new StudentListAdapter(getActivity(), new StudentListAdapter.StudentDiff(), studentViewModel,gradesViewModel);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        oldGrades = new HashSet<>();
//        studentViewModel.deleteAllStudents();


        studentViewModel.getAllStudents().observe(getViewLifecycleOwner(), students1 -> {
            adapter.submitList(students1);
            gradesViewModel.getAllGrades().observe(getViewLifecycleOwner(), grades -> {
                Log.d(TAG, " in get all grades");
                for (StudentGradesEntity studentGradesEntity : grades) {
                    Log.d(TAG, " for ul din get all grades");
                    oldGrades.add(studentGradesEntity.getStudentId());
                }
                List<StudentGradesEntity> newGrades = new ArrayList<>();
                for (StudentEntity studentEntity : students1) {
                    Log.d(TAG + " stud", String.valueOf(studentEntity.getId()));
                    Log.d(TAG + " old", String.valueOf(oldGrades));

                    newGrades.add(new StudentGradesEntity(
                            studentEntity.getId(),
                            "[]",
                            "[]",
                            "[]",
                            "[]",
                            "[]"
                    ));
                }
                for (StudentGradesEntity studentGradesEntity : newGrades) {
//                Log.d(TAG,String.valueOf(studentGradesEntity.getStudentId()));

                    if (!oldGrades.contains(studentGradesEntity.getStudentId()) || oldGrades.isEmpty()) {
                        oldGrades.add(studentGradesEntity.getStudentId());
                        gradesViewModel.addGrade(studentGradesEntity);
                    }
                }

                //verificam daca ceva s-a sters
//                for(int i: oldGrades){
//                    for(StudentEntity studentEntity: students1){
//
//                    }
//                }
            });


//            if(!oldGrades.isEmpty()){
//                for(StudentGradesEntity studentGradesEntity1: oldGrades){
//                    Log.d(TAG+"vechi",String.valueOf(studentGradesEntity1.getStudentId()));
//                }
//            }


        });

        FloatingActionButton fab = view.findViewById(R.id.addStudentFAB);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NewStudentActivity.class);
                startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
//                studentViewModel.deleteAllStudents();
            }
        });
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(), RecyclerView.VERTICAL, false);
//        recyclerView.setLayoutManager(linearLayoutManager);

//        studentList = new DataSource().getStudents();
//        for (Student student: students){
//            studentList.add( new StudentModel(
//                    student.getFirstName(),
//                    student.getLastName(),
//                    student.getBirthday(),
//                    student.getHeight(),
//                    student.getWeight(),
//                    student.getEmail(),
//                    student.getAddress(),
//                    student.getParentFullName(),
//                    student.getParentEmail(),
//                    student.getParentPhone(),
//                    student.getSex()
//            ));
//        }
//        studentAdapter = new StudentAdapter(getActivity(),studentList);
//        recyclerView.setAdapter(studentAdapter);

        return view;

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "on activity result student fragment");
        super.onActivityResult(requestCode, resultCode, data);
        Bundle bundle = data.getExtras();
        StudentModel studentModel = (StudentModel) bundle.getSerializable(NewStudentActivity.EXTRA_REPLY);
        Log.d(TAG, studentModel.getLastName());
        Log.d(TAG, studentModel.getParentFullName());
        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            StudentEntity studentEntity = new StudentEntity(
                    studentModel.getFirstName(),
                    studentModel.getLastName(),
                    studentModel.getBirthday(),
                    studentModel.getHeight(),
                    studentModel.getWeight(),
                    studentModel.getEmail(),
                    studentModel.getAddress(),
                    studentModel.getParentFullName(),
                    studentModel.getParentEmail(),
                    studentModel.getParentPhone(),
                    studentModel.getSex()
            );
            studentViewModel.insert(studentEntity);
        } else {
            Toast.makeText(
                    getActivity().getBaseContext(),
                    "not possible",
                    Toast.LENGTH_LONG).show();
        }
    }


}
