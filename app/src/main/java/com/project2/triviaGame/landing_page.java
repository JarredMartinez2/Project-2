package com.project2.triviaGame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.project2.triviaGame.databinding.ActivityLandingPageBinding;

public class landing_page extends AppCompatActivity {

    private TextView textViewUsername;
    private Button adminAreaButton, logoutButton;
    private SharedPreferences sharedPreferences;

    private static final String PREF_NAME = "user_pref";
    private static final String KEY_USERNAME = "username";
    private AppBarConfiguration appBarConfiguration;
    private ActivityLandingPageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);

        textViewUsername = findViewById(R.id.textViewUsername);
        adminAreaButton = findViewById(R.id.buttonAdminArea);
        logoutButton = findViewById(R.id.buttonLogout);
        sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        //Get the username from SharedPreferences
        String username = sharedPreferences.getString(KEY_USERNAME, "");

        //Show the username
        textViewUsername.setText("Logged in as : " + username);

        boolean adminStatus = checkAdmin(username);

        if (adminStatus) {
            adminAreaButton.setVisibility(View.VISIBLE);
        }

        adminAreaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //functionality for next part of the assignment.
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                sharedPreferences.edit().clear().apply();
                startActivity(new Intent(landing_page.this, MainActivity.class));
            }
        });
    }
            private boolean checkAdmin(String username) {
        //simple (not elegant) solution for only part 02:
        return username.equals("admin2");
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_landing_page);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}