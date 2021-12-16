package com.example.proiect_real.activities;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;

import android.app.Activity;
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

import com.example.proiect_real.R;
import com.example.proiect_real.dao.UserDao;
import com.example.proiect_real.database.ClassroomRoomDatabase;
import com.example.proiect_real.entity.UserEntity;
import com.example.proiect_real.models.UserModel;
import com.example.proiect_real.view_model.UserViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class LogInActivity extends AppCompatActivity {

    private final String TAG = LogInActivity.class.getSimpleName();

    public final static String USERNAME_KEY = "username";

    EditText usernameEditText;
    EditText passwordEditText;
    Button login;
    TextView counterTextView;
    TextView textViewRegister;
    private int counter = 5;
    ActivityResultLauncher<Intent> startActivityForResult;
    List<UserEntity> userEntityList;
    UserViewModel userViewModel;
    UserEntity userEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEditText = (EditText) findViewById(R.id.username);
        passwordEditText = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.loginButton);
        counterTextView = (TextView) findViewById(R.id.textView3);
        counterTextView.setVisibility(View.GONE);
        textViewRegister = findViewById(R.id.textViewRegister);


        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        userEntityList = userViewModel.getAllUsers();

        textViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LogInActivity.this, RegisterActivity.class));
            }
        });

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
                Log.d(TAG,"CLICK PE LOGIN");
                try {
                    if(validateCredentials(usernameEditText.getText().toString(),
                            passwordEditText.getText().toString())){
                        try {
                            userEntity = userViewModel.getUser(usernameEditText.getText().toString(),passwordEditText.getText().toString());
                            Toast.makeText(getApplicationContext(), "Redirecting...", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LogInActivity.this, MainScreenActivity.class);
                            UserModel userModel = new UserModel(userEntity.getUserName(),
                                    userEntity.getPassword(),
                                    userEntity.getEmail(),
                                    userEntity.getAccountType());
                            intent.putExtra(USERNAME_KEY, userModel);
                            startActivityForResult.launch(intent);
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }else{
                        Toast.makeText(getApplicationContext(), "Wrong Credentials", Toast.LENGTH_SHORT).show();
                    }
                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });


    }

    private boolean validateCredentials(String username, String userPassword) throws ExecutionException, InterruptedException {
        if (userViewModel.checkValidLogin(username,userPassword)) {
            Log.d(TAG, "Login button clicked!");
            return true;
        } else {
            return false;
        }
    }

    public void hideSoftKeyboard() {
        InputMethodManager inputMethodManager =
                (InputMethodManager) LogInActivity.this.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        if (inputMethodManager.isAcceptingText()) {
            inputMethodManager.hideSoftInputFromWindow(
                    LogInActivity.this.getCurrentFocus().getWindowToken(),
                    0
            );
        }
    }

}