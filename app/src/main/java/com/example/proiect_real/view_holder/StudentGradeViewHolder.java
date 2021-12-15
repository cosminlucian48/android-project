package com.example.proiect_real.view_holder;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proiect_real.R;
import com.example.proiect_real.adapters.StudentGradeListAdapter;
import com.example.proiect_real.data.DataConverter;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.List;

public class StudentGradeViewHolder extends RecyclerView.ViewHolder {
    private final TextView studentNameView;
    private final TextView studentGrade1;
    private final TextView studentGrade2;
    private final TextView studentGrade3;
    private final TextView studentGrade4;
    private final TextView studentGrade5;

    public final Button addGradeButton;
    public final Button deleteGradeButton;
    public MaterialCardView gradesConstraintView;

    private final String TAG = StudentGradeViewHolder.class.getSimpleName();


    private StudentGradeViewHolder(View itemView) {
        super(itemView);
        studentNameView = itemView.findViewById(R.id.studentNameGrade);
        studentGrade1 = itemView.findViewById(R.id.gradeSubject1);
        studentGrade2 = itemView.findViewById(R.id.gradeSubject2);
        studentGrade3 = itemView.findViewById(R.id.gradeSubject3);
        studentGrade4 = itemView.findViewById(R.id.gradeSubject4);
        studentGrade5 = itemView.findViewById(R.id.gradeSubject5);

        addGradeButton = itemView.findViewById(R.id.addGrade);
        deleteGradeButton = itemView.findViewById(R.id.deleteGrade);
        gradesConstraintView = itemView.findViewById(R.id.gradesConstraintView);

    }

    public void bind(String name, String grades1,String grades2,String grades3,String grades4,String grades5){
        studentGrade1.setText(transformGrades(grades1));
        studentGrade2.setText(transformGrades(grades2));
        studentGrade3.setText(transformGrades(grades3));
        studentGrade4.setText(transformGrades(grades4));
        studentGrade5.setText(transformGrades(grades5));
        studentNameView.setText(name);
    }

    private String transformGrades(String gradesString){
        List<Integer> gradesList1 = new ArrayList<>();
        gradesList1 = DataConverter.fromJsonToList(gradesString);
        String finalGrades = "";
        for(int note: gradesList1){
            if(finalGrades.equals("")){
                finalGrades = "" + note;
            }else {
                finalGrades = finalGrades + ", " + note;
            }
        }
        return  finalGrades;
    }

    public static StudentGradeViewHolder create(ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_grades,parent,false);
        return new StudentGradeViewHolder(view);
    }
}
