package com.project2.triviaGame;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.project2.triviaGame.databinding.ActivityLoginPageBinding;

public class login_page extends AppCompatActivity {

    private EditText editTextUsername, editTextPassword;
    private AppBarConfiguration appBarConfiguration;
    private ActivityLoginPageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);

        Button buttonLogin = findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUsername.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                if (validateCredentials(username, password)) {
                    //then it goes to the landing page
                    Intent intent = new Intent(login_page.this, landing_page.class);
                    //Pass the username to landing page
                    intent.putExtra("username",username);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(login_page.this, "Invalid username or password. womp womp",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_login_page);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    //This method checks to see if the entered username and password are valid.
    //This is a primitive checker and will be updated when more functionality is intended.
    //ie: when "create a user" button is implemented - there will be more users defined.
    private boolean validateCredentials(String username, String password) {
        return (username.equals("testuser1") && password.equals("testuser1")) ||
                (username.equals("admin2") && password.equals("admin2"));
    }
}