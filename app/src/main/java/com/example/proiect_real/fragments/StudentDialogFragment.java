package com.example.proiect_real.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.proiect_real.R;
import com.example.proiect_real.entity.StudentEntity;
import com.example.proiect_real.models.StudentModel;

import java.io.Serializable;

public class StudentDialogFragment extends AppCompatDialogFragment  {

    private EditText userInpur;
    private TextView student_dialog_textview;
    private static final String TAG = StudentDialogFragment.class.getSimpleName();
    private StudentModel studentModel;

    public static StudentDialogFragment newInstance(StudentEntity studentEntity) {
        StudentDialogFragment f = new StudentDialogFragment();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        StudentModel newStudentModel = new StudentModel(
                    studentEntity.getFirstName(),
                    studentEntity.getLastName(),
                    studentEntity.getBirthday(),
                    studentEntity.getHeight(),
                    studentEntity.getWeight(),
                    studentEntity.getEmail(),
                    studentEntity.getAddress(),
                    studentEntity.getParentFullName(),
                    studentEntity.getParentEmail(),
                    studentEntity.getParentPhone(),
                    studentEntity.getSex()
        );
        args.putSerializable("title", (Serializable) newStudentModel);
        f.setArguments(args);
        Log.d(TAG,"nou instanta");
        Log.d(TAG,args.toString());

        return f;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        studentModel =(StudentModel) getArguments().getSerializable("title");
        Log.d(TAG,"in on create simplu");
//        Log.d(TAG,String.valueOf(title));

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        View view = inflater.inflate(R.layout.student_dialog_fragment,null);
        TextView dialog_student_first_name =(TextView)  view.findViewById(R.id.dialog_student_first_name);
        dialog_student_first_name.setText(studentModel.getFirstName());

        TextView dialog_student_last_name =(TextView)  view.findViewById(R.id.dialog_student_last_name);
        dialog_student_last_name.setText(studentModel.getLastName());

        TextView dialog_student_birthday =(TextView)  view.findViewById(R.id.dialog_student_birthday);
        dialog_student_birthday.setText(studentModel.getBirthday());

        TextView dialog_student_email =(TextView)  view.findViewById(R.id.dialog_student_email);
        dialog_student_email.setText(studentModel.getEmail());

        TextView dialog_student_address =(TextView)  view.findViewById(R.id.dialog_student_address);
        dialog_student_address.setText(studentModel.getAddress());

        TextView dialog_student_height =(TextView)  view.findViewById(R.id.dialog_student_height);
        dialog_student_height.setText(studentModel.getHeight().toString());

        TextView dialog_student_weight =(TextView)  view.findViewById(R.id.dialog_student_weight);
        dialog_student_weight.setText(studentModel.getWeight().toString());


        TextView dialog_student_parent_fullname =(TextView)  view.findViewById(R.id.dialog_student_parent_fullname);
        dialog_student_parent_fullname.setText(studentModel.getParentFullName());

        TextView dialog_student_parent_email =(TextView)  view.findViewById(R.id.dialog_student_parent_email);
        dialog_student_parent_email.setText(studentModel.getParentEmail());

        TextView dialog_student_parent_phone =(TextView)  view.findViewById(R.id.dialog_student_parent_phone);
        dialog_student_parent_phone.setText(studentModel.getParentPhone());

        Button dialog_student_call_parent = view.findViewById(R.id.dialog_student_call_parent);
        dialog_student_call_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+studentModel.getParentPhone()));
                startActivity(intent);
            }
        });

        Log.d(TAG,"in on create view");
//        Log.d(TAG,String.valueOf(title));



//        builder.setView(view)
//                .setTitle(title)
//                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                })
//                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                });



        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = ViewGroup.LayoutParams.WRAP_CONTENT;
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
    }

//    @NonNull
//    @Override
//    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
//        int title = getArguments().getInt("title");
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//
//        LayoutInflater inflater = getActivity().getLayoutInflater();
//        View view = inflater.inflate(R.layout.student_dialog_fragment,null);
//
//        builder.setView(view)
//                .setTitle(title)
//                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                })
//                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                });
//
//        return builder.create();
//
//    }

}
