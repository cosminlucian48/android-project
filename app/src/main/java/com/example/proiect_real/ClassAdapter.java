package com.example.proiect_real;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proiect_real.models.ClassModel;

import java.util.List;

public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.ClassViewHolder> {

    private static final String TAG = ClassAdapter.class.getSimpleName();
    private List<ClassModel> classes;

    public ClassAdapter(List<ClassModel> classes) {
        this.classes = classes;
    }
    @NonNull
    @Override
    public ClassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_view_class, parent, false);
        return new ClassViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassViewHolder holder, int position) {
        String cname = classes.get(position).getClassName();
        holder.cname.setText(cname);
        String teacher = classes.get(position).getClassTeacher();
        holder.teacher.setText(teacher);
        String teacherMail = classes.get(position).getTeacherMail();
        holder.teacherMail.setText(teacherMail);
        String cdescription = classes.get(position).getClassDescription();
        holder.cdescription.setText(cdescription);
    }

    @Override
    public int getItemCount() {
        return classes.size();
    }

    static class ClassViewHolder extends RecyclerView.ViewHolder {
        private TextView cname;
        private TextView teacher;
        private TextView teacherMail;
        private TextView cdescription;

        ClassViewHolder(View itemView) {
            super(itemView);
            cname = itemView.findViewById(R.id.cname);
            teacher = itemView.findViewById(R.id.teacher);
            teacherMail = itemView.findViewById(R.id.teacherMail);
            cdescription = itemView.findViewById(R.id.classDescription);
        }
    }
}
