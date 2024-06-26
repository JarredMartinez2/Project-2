package com.project2.triviaGame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.project2.triviaGame.Database.ProjectRepository;
import com.project2.triviaGame.databinding.ActivityLandingPageBinding;

public class landing_page extends AppCompatActivity {

    private TextView textViewUsername;
    private Button adminAreaButton, logoutButton, deleteSetButton, addSetButton, remixButton, playButton, leaderboardButton;
    private SharedPreferences sharedPreferences;

    private static final String PREF_NAME = "user_pref";
    private static final String KEY_USERNAME = "username";
    private AppBarConfiguration appBarConfiguration;
    private ActivityLandingPageBinding binding;

    private ProjectRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);

        textViewUsername = findViewById(R.id.textViewUsername);
        adminAreaButton = findViewById(R.id.buttonAdminArea);
        deleteSetButton = findViewById(R.id.deleteSetButton);
        addSetButton = findViewById(R.id.newCardSet);
        remixButton = findViewById(R.id.remixCardSets);
        logoutButton = findViewById(R.id.buttonLogout);
        playButton = findViewById(R.id.playButton);
        leaderboardButton = findViewById(R.id.leaderboard);


        boolean admin = getIntent().getBooleanExtra("admin", false);
        String username = getIntent().getStringExtra("username");

        //Show the username
        textViewUsername.setText(getString(R.string.logged_in_as) + username);

        if (admin) {
            adminAreaButton.setVisibility(View.VISIBLE);
        }

        adminAreaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(landing_page.this, admin_page.class));
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                sharedPreferences.edit().clear().apply();
                startActivity(new Intent(landing_page.this, MainActivity.class));
            }
        });

        deleteSetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(landing_page.this, deleteSetPage.class));
            }
        });

        addSetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(landing_page.this, add_set_page.class));
            }
        });

        remixButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(landing_page.this, remix_set_page.class));
            }
        });

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(landing_page.this, selectTriviaSetPage.class));
            }
        });

        leaderboardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(landing_page.this, leaderboard_page.class));
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_landing_page);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}