package com.example.proiect_real;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentsViewHolder> {

    private static final String TAG = StudentAdapter.class.getSimpleName();
    private List<StudentModel> students;

    StudentAdapter(List<StudentModel> studentsList) {
        this.students = studentsList;
    }

    @NonNull
    @Override
    public StudentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder()");
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_view_student, parent, false);
        return new StudentsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentsViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder(), position= " + position);
        String name = (position+1)+ " "+ students.get(position).getFirstName() + " " + students.get(position).getLastName();
        holder.fname.setText(name);

//        String address = students.get(position).getAddress();
//        holder.city.setText(address);

        String birthday = students.get(position).getBirthday();
        holder.birthday.setText(birthday);

        Boolean sex = students.get(position).getSex();
        if(sex){
            holder.photo.setImageResource(R.drawable.female_placeholder);
        }else{
            holder.photo.setImageResource(R.drawable.male_placeholder);
        }


    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    static class StudentsViewHolder extends RecyclerView.ViewHolder {
        private TextView fname;
        private TextView birthday;
        private ImageView photo;

        StudentsViewHolder(View itemView) {
            super(itemView);
            fname = itemView.findViewById(R.id.name);
            birthday = itemView.findViewById(R.id.birthday);
            photo = itemView.findViewById(R.id.profilePhoto);
        }
    }
}
