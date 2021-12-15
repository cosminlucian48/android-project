package com.example.proiect_real.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proiect_real.ClassAdapter;
import com.example.proiect_real.entity.ClassEntity;
import com.example.proiect_real.models.ClassModel;
import com.example.proiect_real.DataSource;
import com.example.proiect_real.R;
import com.example.proiect_real.view_model.ClassViewModel;
import com.example.proiect_real.view_model.StudentViewModel;
import com.example.proiect_real.view_model.UserViewModel;

import java.util.ArrayList;
import java.util.List;

public class ClassesFragment extends Fragment {
    private RecyclerView recyclerView;
    private ClassViewModel classViewModel;
    private static final String TAG = ClassesFragment.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_classes, container, false);
        recyclerView = view.findViewById(R.id.classesView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

//        List<ClassModel> classList = new DataSource().getClasses();
//        List<ClassEntity> classEntityList = new ArrayList<ClassEntity>();
//        for (ClassModel classModel : classList) {
//            classEntityList.add(new ClassEntity(classModel.getClassName(),
//                    classModel.getClassTeacher(),
//                    classModel.getTeacherMail(),
//                    classModel.getClassDescription()));
//        }
        classViewModel = new ViewModelProvider(this).get(ClassViewModel.class);
//        classViewModel.insertClasses(classEntityList);
        List<ClassModel> classModelList = new ArrayList<>();
        List<ClassEntity> classEntityList = classViewModel.getAllClasses();
        for (ClassEntity classEntity : classEntityList) {
            classModelList.add(new ClassModel(classEntity.getClassName(),
                    classEntity.getClassTeacher(),
                    classEntity.getTeacherMail(),
                    classEntity.getClassDescription()));
        }


        ClassAdapter classAdapter = new ClassAdapter(classModelList);
        recyclerView.setAdapter(classAdapter);

        return view;
    }
}
