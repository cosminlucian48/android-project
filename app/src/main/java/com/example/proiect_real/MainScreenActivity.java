package com.example.proiect_real;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.List;

public class MainScreenActivity extends AppCompatActivity {

    Button logoutButton;

    public final static String LOGOUT_KEY = "logoutkey";
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        drawerLayout = findViewById(R.id.my_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout, R.string.nav_open, R.string.nav_close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        logoutButton = (Button) findViewById(R.id.logoutButton);
        Bundle bundle = getIntent().getExtras();

        RecyclerView recyclerView  = (RecyclerView) findViewById(R.id.tracksView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        List<Track> trackList = new DataSource().getTracks();

        TrackAdapter trackAdapter = new TrackAdapter(trackList);
        recyclerView.setAdapter(trackAdapter);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(MainScreenActivity.this, LogInActivity.class);
//                if(bundle != null){
//                    String message = bundle.getString(LogInActivity.USERNAME_KEY);
//                    intent.putExtra(LOGOUT_KEY,message);
//                }
//
//                startActivity(intent);
                String message = "Ana";
                if(bundle != null){
//                    message = "Andrei";
                    message = bundle.getString(LogInActivity.USERNAME_KEY);

                }
                Intent intent = new Intent();
                intent.putExtra(LOGOUT_KEY, message);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}