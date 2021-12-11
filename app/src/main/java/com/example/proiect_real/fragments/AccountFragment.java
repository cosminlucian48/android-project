package com.example.proiect_real.fragments;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proiect_real.entity.StudentEntity;
import com.example.proiect_real.activities.NewStudentActivity;
import com.example.proiect_real.R;
import com.example.proiect_real.adapters.StudentListAdapter;
import com.example.proiect_real.view_model.StudentViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AccountFragment extends Fragment {
    private static final String TAG = AccountFragment.class.getSimpleName();
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
    private StudentViewModel studentViewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_account,container,false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        final StudentListAdapter adapter = new StudentListAdapter(getActivity(),new StudentListAdapter.StudentDiff(),studentViewModel);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        studentViewModel = new ViewModelProvider(this).get(StudentViewModel.class);
//        studentViewModel.deleteAllStudents();
        studentViewModel.getAllStudents().observe(getViewLifecycleOwner(), students -> {
            adapter.submitList(students);
        });

        FloatingActionButton fab = view.findViewById(R.id.fab);
//        fab.setOnClickListener( v -> {
//
//        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NewStudentActivity.class);
                startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
            }
        });

//        Button button = getActivity().

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


//        List<StudentModel> studentList = new DataSource().getStudents();
//        ArrayList<Student> studentArrayList = new ArrayList<>();
//        for(StudentModel studentModel: studentList){
//            studentArrayList.add( new Student(
//                    studentModel.getFirstName(),
//                    studentModel.getLastName(),
//                    studentModel.getBirthday(),
//                    studentModel.getHeight(),
//                    studentModel.getWeight(),
//                    studentModel.getEmail(),
//                    studentModel.getAddress(),
//                    studentModel.getParentFullName(),
//                    studentModel.getParentEmail(),
//                    studentModel.getParentPhone(),
//                    studentModel.getSex()
//            ));
//        }

//        new Thread(){
//            @Override
//            public void run() {
////                for (Student student : studentArrayList) {
////                    ClassroomRoomDatabase.getDatabase(getActivity()).studentDao().insert(student);
////                }
//
//                List<Student> students = ClassroomRoomDatabase.getDatabase(getActivity()).studentDao().getAllStudents();
//                for (Student student : students) {
//                    Log.d(TAG, "Student entity name is: " + student.firstName);
//                }
//
//            }
//        }.start();
    }



    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            StudentEntity studentEntity =new StudentEntity(
                    data.getStringExtra(NewStudentActivity.EXTRA_REPLY),
                    "Andrei",
                    "1august2000",
                    100D,
                    80,
                    "cosmin@yahoo.com",
                    "imdevaDeparte",
                    "mama si tata",
                    "m,ama@yahoo.com",
                    "07nam cartela",
                    true
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
