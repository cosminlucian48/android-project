package com.example.proiect_real.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proiect_real.adapters.ClassAdapter;
import com.example.proiect_real.entity.ClassEntity;
import com.example.proiect_real.models.ClassModel;
import com.example.proiect_real.R;
import com.example.proiect_real.view_model.ClassViewModel;

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
//        List<ClassEntity> classEntityList = new ArrayList<>();
//        classEntityList.add(new ClassEntity("Mathematics","John Doe","jdoe@gmail.com","Mathematics includes the study of such topics as numbers, formulas and related structures, shapes and spaces in which they are contained, and quantities and their changes."));
//        classEntityList.add(new ClassEntity("English","Johnina Doe","jninadoe@gmail.com","English is a West Germanic language of the Indo-European language family, originally spoken by the inhabitants of early medieval England."));
//        classEntityList.add(new ClassEntity("Science","Klaus von Study","kl@outlook.com","Science is a systematic enterprise that builds and organizes knowledge in the form of testable explanations and predictions about the universe."));
//        classEntityList.add(new ClassEntity("Informatics","Ben Awad","benn@yahoo.com","Informatics is the study of computational systems, especially those for data storage and retrieval."));
//        classEntityList.add(new ClassEntity("Music","Horia The Teacher","horia@yahoo.com","Music is the art of arranging sounds in time through the elements of melody, harmony, rhythm, and timbre. It is one of the universal cultural aspects of all human societies. General definitions of music include common elements such as pitch , rhythm, dynamics, and the sonic qualities of timbre and texture."));
////        classViewModel.deleteAllClasses();
//        classViewModel.insertClasses(classEntityList);
        List<ClassModel> classModelList = new ArrayList<>();
        List<ClassEntity> classEntityList2 = classViewModel.getAllClasses();
        for (ClassEntity classEntity : classEntityList2) {
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
