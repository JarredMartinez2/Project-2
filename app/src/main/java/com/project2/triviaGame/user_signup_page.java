package com.project2.triviaGame;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.navigation.ui.AppBarConfiguration;
import com.project2.triviaGame.Database.ProjectRepository;
import com.project2.triviaGame.Database.entities.UserDB;

public class user_signup_page extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ProjectRepository repository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        repository = ProjectRepository.getRepository(getApplication());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_signup_page);

        EditText editTextUsername = findViewById(R.id.editTextUsername_userpage);
        EditText editTextPassword = findViewById(R.id.editTextPassword_userpage);
        Button buttonConfirm = findViewById(R.id.confirm_button_userpage);

        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUsername.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();
                if (!username.isEmpty() && !password.isEmpty()) {
                    UserDB user = new UserDB(username,password);
                    repository.insertUser(user);
                    //Toast for successful sign up
                    Toast.makeText(user_signup_page.this, "Sign-up successful", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(user_signup_page.this, "Please enter both username and password.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}