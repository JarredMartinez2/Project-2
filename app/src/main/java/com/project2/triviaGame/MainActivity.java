package com.project2.triviaGame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private static final String PREF_NAME = "user_pref";
    private static final String KEY_LOGGED_IN = "logged in";

    public static final String TAG = "Project2_db";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        //Check if the user is logged into the game.
        if (isLoggedIn()) {
            goToLandingPage();
        }
        //Buttons for the login page as well as the create account:

        Button loginButton = findViewById(R.id.buttonLogin);
        Button createAccountButton = findViewById(R.id.buttonCreateAccount);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log in activity
                startActivity(new Intent(MainActivity.this, login_page.class));
            }
        });

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Create Account Activity (if needed - not developed yet)
                //startActivity(new Intent(MainActivity.this, AccountCreateActivity.class));
            }
        });
    }

    private boolean isLoggedIn() {
        return sharedPreferences.getBoolean(KEY_LOGGED_IN, false);
    }

    private void goToLandingPage() {
        startActivity(new Intent(MainActivity.this, landing_page.class));
        finish();
    }

}