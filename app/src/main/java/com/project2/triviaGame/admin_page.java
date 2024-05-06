package com.project2.triviaGame;

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

import com.project2.triviaGame.Database.ProjectRepository;
import com.project2.triviaGame.Database.entities.UserDB;
import com.project2.triviaGame.databinding.ActivityAdminPageBinding;

public class admin_page extends AppCompatActivity {
    private EditText editTextNewAdminUsername, editTextNewAdminPassword, editTextDeleteUser;
    private ProjectRepository repository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);
        editTextNewAdminUsername = findViewById(R.id.editTextNewAdminUsername);
        editTextNewAdminPassword = findViewById(R.id.editTextNewAdminPassword);
        editTextDeleteUser = findViewById(R.id.editTextDeleteUser);
        Button buttonCreateNewAdminUser = findViewById(R.id.buttonCreateNewAdminUser);
        Button buttonDeleteUser = findViewById(R.id.buttonDeleteUser);
        repository = ProjectRepository.getRepository(getApplication());
        buttonCreateNewAdminUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newAdminUsername = editTextNewAdminUsername.getText().toString().trim();
                String newAdminPassword = editTextNewAdminPassword.getText().toString().trim();
                Toast.makeText(admin_page.this, "New admin user created: " + newAdminUsername, Toast.LENGTH_SHORT).show();
                UserDB user = new UserDB(newAdminUsername,newAdminPassword);
                user.setAdmin(true);
                repository.insertUser(user);
            }
        });

        buttonDeleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userToDelete = editTextDeleteUser.getText().toString().trim();
                //Need to implement logic to delete the user with the String userToDelete
                Toast.makeText(admin_page.this, "User deleted: " + userToDelete, Toast.LENGTH_SHORT).show();
                LiveData<UserDB> userObserver = repository.getUserByUserName(userToDelete);
                userObserver.observe(admin_page.this, userDB -> {
                    if (userDB != null) {
                        if (userToDelete.equals(userDB.getUserName())) {
                            //insert delete code!
                            repository.deleteUser(userDB);
                        }
                    } else {
                        Toast.makeText(admin_page.this, "user is null", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}