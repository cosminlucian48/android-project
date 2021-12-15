package com.example.proiect_real.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.proiect_real.R;
import com.example.proiect_real.models.StudentModel;

import org.apache.commons.lang3.math.NumberUtils;

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
//            Log.d("CIUDAT",newStudentFirstName.getText().toString());
//            NumberUtils.isParsable(newStudentHeight.getText().toString());
//            Log.d("Ciudat: " + "1", String.valueOf(TextUtils.isEmpty(newStudentFirstName.getText())));
//            Log.d("Ciudat: "+ "2", String.valueOf(TextUtils.isEmpty(newStudentLastName.getText())));
//            Log.d("Ciudat: "+ "3", String.valueOf(newStudentBirthDate.getYear() > 2021));
//            Log.d("Ciudat: "+ "4", String.valueOf(!NumberUtils.isParsable(newStudentHeight.getText().toString())));
//            Log.d("Ciudat: "+ "5", String.valueOf(!NumberUtils.isParsable(newStudentWeight.getText().toString())));
//            Log.d("Ciudat: "+ "6", String.valueOf(TextUtils.isEmpty(newStudentEmail.getText())));
//            Log.d("Ciudat: "+ "7", String.valueOf(TextUtils.isEmpty(newStudentAddress.getText())));
//            Log.d("Ciudat: "+ "8", String.valueOf(TextUtils.isEmpty(newStudentParentFullName.getText())));
//            Log.d("Ciudat: "+ "9", String.valueOf(TextUtils.isEmpty(newStudentParentEmail.getText())));
//            Log.d("Ciudat: "+ "10", String.valueOf(TextUtils.isEmpty(newStudentParentPhone.getText())));

            if (TextUtils.isEmpty(newStudentFirstName.getText()) ||
                    TextUtils.isEmpty(newStudentLastName.getText()) ||
                    newStudentBirthDate.getYear() > 2021 ||
                    !NumberUtils.isParsable(newStudentHeight.getText().toString()) ||
                    !NumberUtils.isParsable(newStudentWeight.getText().toString()) ||
                    TextUtils.isEmpty(newStudentEmail.getText()) ||
                    TextUtils.isEmpty(newStudentAddress.getText()) ||
                    TextUtils.isEmpty(newStudentParentFullName.getText()) ||
                    TextUtils.isEmpty(newStudentParentEmail.getText()) ||
                    TextUtils.isEmpty(newStudentParentPhone.getText())) {
                Log.d("Ciudattt", "problema");
                Toast.makeText(getApplicationContext(), "Wrong Data!", Toast.LENGTH_SHORT).show();
//                setResult(RESULT_CANCELED, replyIntent);
            } else {
                String firstName = newStudentFirstName.getText().toString();
                String lastName = newStudentLastName.getText().toString();
                String birthdate = newStudentBirthDate.getDayOfMonth() + " " + newStudentBirthDate.getMonth() + " " + newStudentBirthDate.getYear();
                Double height = Double.valueOf(newStudentHeight.getText().toString());
                Integer weight = Integer.valueOf(newStudentWeight.getText().toString());
                String email = newStudentEmail.getText().toString();
                String address = newStudentAddress.getText().toString();
                String parentFullName = newStudentParentFullName.getText().toString();
                String parentEmail = newStudentParentEmail.getText().toString();
                String parentPhone = newStudentParentPhone.getText().toString();
                String sex = (String) ((RadioButton) findViewById(newStudentSexGroup.getCheckedRadioButtonId())).getText();
                Log.d("SEXUL", sex);
                boolean realSex;
                if (sex.equals("F")) {
                    realSex = true;
                } else {
                    realSex = false;
                }
                Log.d("CIUDAT: ", firstName);
                Log.d("CIUDAT: ", String.valueOf(firstName.equals("")));
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
                replyBundle.putSerializable(EXTRA_REPLY, studentModel);
                replyIntent.putExtras(replyBundle);
                setResult(RESULT_OK, replyIntent);
                finish();
            }

        });

        final Button button1 = findViewById(R.id.cancel_create_student_button);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra(EXTRA_REPLY,"test");
                setResult(RESULT_CANCELED, new Intent());
                finish();
            }
        });
    }
}