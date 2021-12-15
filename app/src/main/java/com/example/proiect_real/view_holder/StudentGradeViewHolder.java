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

public class StudentGradeViewHolder extends RecyclerView.ViewHolder {
    private final TextView studentNameView;
    private final TextView studentGrade1;
    private final TextView studentGrade2;
    private final TextView studentGrade3;
    private final TextView studentGrade4;
    private final TextView studentGrade5;

    public final Button addGradeButton;
    public final Button deleteGradeButton;
    public ConstraintLayout gradesConstraintView;

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
//        Log.d(TAG,name);
//        Log.d(TAG + "1",grades1);
//        Log.d(TAG+ "2",grades2);
//        Log.d(TAG + "3",grades3);
//        Log.d(TAG+ "4",grades4);
//        Log.d(TAG+ "5",grades5);
        studentGrade1.setText(grades1);
        studentGrade2.setText(grades2);
        studentGrade3.setText(grades3);
        studentGrade4.setText(grades4);
        studentGrade5.setText(grades5);
        studentNameView.setText(name);
    }

    public static StudentGradeViewHolder create(ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_grades,parent,false);
        return new StudentGradeViewHolder(view);
    }
}
