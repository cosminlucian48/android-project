//package com.example.proiect_real.Deprecated;
//
//import android.content.Context;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.constraintlayout.widget.ConstraintLayout;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.proiect_real.R;
//import com.example.proiect_real.models.StudentModel;
//
//import java.util.List;
//
//public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentsViewHolder>{
//
//    private static final String TAG = StudentAdapter.class.getSimpleName();
//    Context ctx;
//    int mStackLevel = 0;
//    private List<StudentModel> students;
//
//    public StudentAdapter(Context ctx, List<StudentModel> students) {
//        this.ctx = ctx;
//        this.students = students;
//    }
//
//    @NonNull
//    @Override
//    public StudentAdapter.StudentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        Log.d(TAG, "onCreateViewHolder()");
//        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
//        View view = layoutInflater.inflate(R.layout.recycler_view_student, parent, false);
//
//        return new StudentsViewHolder(view);
//    }
//
//
//    @Override
//    public void onBindViewHolder(@NonNull StudentsViewHolder holder, int position) {
//        Log.d(TAG, "onBindViewHolder(), position= " + position);
//        String name = (position + 1) + " " + students.get(position).getFirstName() + " " + students.get(position).getLastName();
//        holder.fname.setText(name);
//        int test = position;
//        String birthday = students.get(position).getBirthday();
//        holder.birthday.setText(birthday);
//
//        Boolean sex = students.get(position).getSex();
//        if (sex) {
//            holder.photo.setImageResource(R.drawable.female_placeholder);
//        } else {
//            holder.photo.setImageResource(R.drawable.male_placeholder);
//        }
//        //student card holder.onclicklistener
//        //long click (apesi lung)
//
//        holder.studentModel.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                showDialog(students.get(test));
//                return false;
//            }
//        });
//
//
//    }
//
//    void showDialog(StudentModel studentModel) {
////        StudentDialogFragment studentDialogFragment = StudentDialogFragment.newInstance(student);
////        studentDialogFragment.show(((FragmentActivity)ctx).getSupportFragmentManager(), "Test sa moara mama");
//    }
//
//    @Override
//    public int getItemCount() {
//        return students.size();
//    }
//
//    static class StudentsViewHolder extends RecyclerView.ViewHolder {
//        private TextView fname;
//        private TextView birthday;
//        private ImageView photo;
//        private ConstraintLayout studentModel;
//
//        StudentsViewHolder(View itemView) {
//            super(itemView);
//            fname = itemView.findViewById(R.id.name);
//            birthday = itemView.findViewById(R.id.birthday);
//            photo = itemView.findViewById(R.id.profilePhoto);
//            studentModel = itemView.findViewById(R.id.studentConstraintView);
//        }
//    }
//
//
//
//}
