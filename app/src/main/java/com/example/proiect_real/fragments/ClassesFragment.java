package com.example.proiect_real.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proiect_real.ClassAdapter;
import com.example.proiect_real.models.ClassModel;
import com.example.proiect_real.DataSource;
import com.example.proiect_real.R;

import java.util.List;

public class ClassesFragment extends Fragment {
    private RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_classes,container,false);
        recyclerView  = view.findViewById(R.id.classesView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        List<ClassModel> classList = new DataSource().getClasses();

        ClassAdapter classAdapter = new ClassAdapter(classList);
        recyclerView.setAdapter(classAdapter);

        return view;
    }
}
