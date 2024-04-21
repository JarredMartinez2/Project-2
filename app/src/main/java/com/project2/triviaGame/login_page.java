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
import androidx.lifecycle.LiveData;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.project2.triviaGame.Database.Project2Database;
import com.project2.triviaGame.Database.ProjectRepository;
import com.project2.triviaGame.Database.entities.UserDB;
import com.project2.triviaGame.databinding.ActivityLoginPageBinding;

public class login_page extends AppCompatActivity {

    private EditText editTextUsername, editTextPassword;
    private AppBarConfiguration appBarConfiguration;
    private ActivityLoginPageBinding binding;

    private ProjectRepository repository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        repository = ProjectRepository.getRepository(getApplication());
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
//
        Button buttonLogin = findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateCredentials();
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
    private void validateCredentials() {
        String username = editTextUsername.getText().toString().trim();
        if (username.isEmpty()) {
            Toast.makeText(this, "User may not be blank", Toast.LENGTH_SHORT).show();
        }
        LiveData<UserDB> userObserver = repository.getUserByUserName(username);
        userObserver.observe(this, userDB -> {
            if (userDB != null) {
                String password = editTextPassword.getText().toString().trim();
                if (password.equals(userDB.getPassWord())) {
                    Intent intent = new Intent(login_page.this, landing_page.class);
                    intent.putExtra("username", username);
                    intent.putExtra("admin", userDB.isAdmin());
                    startActivity(intent);
                } else {
                    toastMaker("Invalid password");
                    editTextPassword.setSelection(0);
                }
            } else {
                toastMaker(String.format("%s is not a valid username", username));
                editTextUsername.setSelection(0);
            }
        });
    }

    private void toastMaker(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}