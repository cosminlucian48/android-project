package com.example.proiect_real.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.proiect_real.R;
import com.example.proiect_real.models.StudentModel;

public class NewStudentActivity extends AppCompatActivity {

    private EditText newStudentFirstName;
    private EditText newStudentLastName;
    private DatePicker newStudentBirthDate;
    private EditText newStudentHeight;
    private EditText newStudentWeight;
    private EditText newStudentEmail;
    private EditText newStudentAddress;
    private EditText newStudentParentFullName;
    private EditText newStudentParentEmail;
    private EditText newStudentParentPhone;
    private RadioGroup newStudentSexGroup;
    private RadioButton newStudentSex;
    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_student);

        newStudentFirstName = findViewById(R.id.newStudentFirstName);
        newStudentLastName = findViewById(R.id.newStudentLastName);
        newStudentBirthDate = findViewById(R.id.newStudentBirthday);
        newStudentHeight = findViewById(R.id.newStudentHeight);
        newStudentWeight = findViewById(R.id.newStudentWeight);
        newStudentEmail = findViewById(R.id.newStudentEmail);
        newStudentAddress = findViewById(R.id.newStudentAddress);
        newStudentParentFullName = findViewById(R.id.newStudentParentFullName);
        newStudentParentEmail = findViewById(R.id.newStudentParentEmail);
        newStudentParentPhone = findViewById(R.id.newStudentParentPhone);
        newStudentSexGroup = findViewById(R.id.newStudentSex);



        final Button button = findViewById(R.id.create_student_button);
        button.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            Bundle replyBundle = new Bundle();
            if (TextUtils.isEmpty(newStudentFirstName.getText())) {
                setResult(RESULT_CANCELED, replyIntent);
            } else {
                String firstName = newStudentFirstName.getText().toString();
                String lastName = newStudentLastName.getText().toString();
                String birthdate = newStudentBirthDate.getDayOfMonth() + " "+ newStudentBirthDate.getMonth() + " " +  newStudentBirthDate.getYear();
                Double height = Double.valueOf(newStudentHeight.getText().toString());
                Integer weight = Integer.valueOf(newStudentWeight.getText().toString());
                String email = newStudentEmail.getText().toString();
                String address = newStudentAddress.getText().toString();
                String parentFullName = newStudentParentFullName.getText().toString();
                String parentEmail = newStudentParentEmail.getText().toString();
                String parentPhone = newStudentParentPhone.getText().toString();
                String sex = (String)((RadioButton)findViewById(newStudentSexGroup.getCheckedRadioButtonId())).getText();
                boolean realSex;
                if(sex=="F"){
                    realSex=true;
                }else{
                    realSex=false;
                }
                StudentModel studentModel = new StudentModel(firstName,
                        lastName,
                        birthdate,
                        height,
                        weight,
                        email,
                        address,
                        parentFullName,
                        parentEmail,
                        parentPhone,
                        realSex);
                replyBundle.putSerializable(EXTRA_REPLY,studentModel);
                replyIntent.putExtras(replyBundle);
                setResult(RESULT_OK, replyIntent);
            }
            finish();
        });
    }
}