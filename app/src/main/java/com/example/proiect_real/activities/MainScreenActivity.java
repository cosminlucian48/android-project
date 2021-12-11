package com.example.proiect_real.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.proiect_real.R;
import com.example.proiect_real.activities.LogInActivity;
import com.example.proiect_real.fragments.AccountFragment;
import com.example.proiect_real.fragments.ClassesFragment;
import com.example.proiect_real.fragments.StudentsFragment;
import com.google.android.material.navigation.NavigationView;

public class MainScreenActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public final static String LOGOUT_KEY = "logoutkey";
    private DrawerLayout drawer;
    public ActionBarDrawerToggle actionBarDrawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.my_drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.nav_open, R.string.nav_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new StudentsFragment()).commit();

            navigationView.setCheckedItem(R.id.nav_students);
        }
    }

    private void logout() {
        Bundle bundle = getIntent().getExtras();
        String message = "Ana";
        if (bundle != null) {
            message = bundle.getString(LogInActivity.USERNAME_KEY);

        }
        Intent intent = new Intent();
        intent.putExtra(LOGOUT_KEY, message);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.nav_account:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AccountFragment()).commit();
                break;
            case R.id.nav_classes:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ClassesFragment()).commit();
                break;
            case R.id.nav_students:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new StudentsFragment()).commit();
                break;
            case R.id.nav_logout:
                logout();
                Toast.makeText(this, "LogOut", Toast.LENGTH_LONG).show();
                break;
            case R.id.nav_git:
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/cosminlucian48"));
                startActivity(browserIntent);
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


}