package com.example.proiect_real;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class LogInActivity extends AppCompatActivity {

    private final String TAG = LogInActivity.class.getSimpleName();

    public final static String USERNAME_KEY = "username";

    EditText usernameEditText;
    EditText passwordEditText;
    Button login;
    TextView counterTextView;
    private int counter = 5;

    ActivityResultLauncher<Intent> startActivityForResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this, "Wrong Credentials", Toast.LENGTH_LONG).show();




        usernameEditText = (EditText) findViewById(R.id.username);
        passwordEditText = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.loginButton);
        counterTextView = (TextView) findViewById(R.id.textView3);
        counterTextView.setVisibility(View.GONE);

//        Bundle bundle = getIntent().getExtras();
//
//        if(bundle != null){
//            String usernameText = bundle.getString(MainScreenActivity.LOGOUT_KEY);
//            usernameEditText.setText(usernameText);
//        }
        startActivityForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == RESULT_OK) {
                    Log.d(TAG, "RESULT_OK");
                    Intent intent = result.getData();
                    if (intent != null) {
                        Bundle bundle = intent.getExtras();
                        String message = bundle.getString(MainScreenActivity.LOGOUT_KEY);
                        usernameEditText.setText(message);
                    }
                } else {
                    Log.d(TAG, "RESULT_CANCELED");
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Login is clicked", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                hideSoftKeyboard();
                validateCredentials(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        });


    }

    private void validateCredentials(String username, String userPassword) {
//        if (username.equals("admin") && userPassword.equals("admin")) {
        if (true) {
            Log.d(TAG, "Login button clicked!");
            Toast.makeText(getApplicationContext(), "Redirecting...", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(LogInActivity.this, MainScreenActivity.class);
            intent.putExtra(USERNAME_KEY, username);
            startActivityForResult.launch(intent);

        } else {
            Toast.makeText(getApplicationContext(), "Wrong Credentials", Toast.LENGTH_LONG).show();
            counterTextView.setVisibility(View.VISIBLE);
            counterTextView.setBackgroundColor(Color.RED);
            counter--;
            counterTextView.setText("Nr of attempts remaining: " + Integer.toString(counter));

            if (counter == 0) {
                login.setEnabled(false);
            }
        }
    }
    public void hideSoftKeyboard() {
        InputMethodManager inputMethodManager =
                (InputMethodManager) LogInActivity.this.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        if(inputMethodManager.isAcceptingText()){
            inputMethodManager.hideSoftInputFromWindow(
                    LogInActivity.this.getCurrentFocus().getWindowToken(),
                    0
            );
        }
    }

}