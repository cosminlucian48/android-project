package com.example.proiect_real.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.proiect_real.fragments.StudentsFragment;
import com.example.proiect_real.view_holder.StudentViewHolder;
import com.example.proiect_real.entity.StudentEntity;
import com.example.proiect_real.fragments.StudentDialogFragment;
import com.example.proiect_real.view_model.StudentViewModel;

public class StudentListAdapter extends ListAdapter<StudentEntity, StudentViewHolder> {
    Context context;
    StudentViewModel studentViewModel;
    public StudentListAdapter(Context ctx, @NonNull DiffUtil.ItemCallback<StudentEntity> diffCallBack, StudentViewModel sm){

        super(diffCallBack);
        this.context = ctx;
        this.studentViewModel = sm;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return StudentViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        StudentEntity current = getItem(position);
        holder.bind(current.getFirstName(), current.getLastName(), current.getBirthday(), current.getSex());


        holder.constraintLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showDialog(current);
                return false;
            }
        });

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                studentViewModel.deleteStudent(current);
            }
        });


    }


    void showDialog(StudentEntity studentEntity) {
        StudentDialogFragment studentDialogFragment = StudentDialogFragment.newInstance(studentEntity);
        studentDialogFragment.show(((FragmentActivity)context).getSupportFragmentManager(), "Test sa moara mama");
    }

    public static class StudentDiff extends DiffUtil.ItemCallback<StudentEntity>{
        @Override
        public boolean areItemsTheSame(@NonNull StudentEntity oldItem, @NonNull StudentEntity newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull StudentEntity oldItem, @NonNull StudentEntity newItem) {
            return oldItem.getFirstName().equals(newItem.getFirstName());
        }
    }
}
