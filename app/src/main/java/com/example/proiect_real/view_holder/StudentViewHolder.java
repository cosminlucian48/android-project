package com.example.proiect_real.view_holder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proiect_real.R;

public class StudentViewHolder extends RecyclerView.ViewHolder {
    private final TextView studentItemView;
    private final TextView studentBirthdayView;
    private final ImageView studentPhotoView;
    public final Button deleteButton;
    public ConstraintLayout constraintLayout;

    private StudentViewHolder(View itemView) {
        super(itemView);
        studentItemView = itemView.findViewById(R.id.studentName);
        studentBirthdayView = itemView.findViewById(R.id.birthday);
        studentPhotoView = itemView.findViewById(R.id.profilePhoto);
        constraintLayout = itemView.findViewById(R.id.studentConstraintView);
        deleteButton = itemView.findViewById(R.id.delete_student);
    }

    public void bind(String firstName,String lastName, String birthday, Boolean sex) {
        studentItemView.setText(firstName+" "+lastName);
        studentBirthdayView.setText(birthday);
        if (sex) {
            studentPhotoView.setImageResource(R.drawable.female_placeholder);
        } else {
            studentPhotoView.setImageResource(R.drawable.male_placeholder);
        }

    }



    public static StudentViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_test, parent, false);
        return new StudentViewHolder(view);
    }
}
