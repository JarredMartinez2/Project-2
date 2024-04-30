package com.project2.triviaGame;

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

import com.project2.triviaGame.databinding.ActivityAdminPageBinding;

public class admin_page extends AppCompatActivity {
    private EditText editTextNewAdminUsername, editTextDeleteUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);
        editTextNewAdminUsername = findViewById(R.id.editTextNewAdminUsername);
        editTextDeleteUser = findViewById(R.id.editTextDeleteUser);
        Button buttonClearLeaderboards = findViewById(R.id.buttonClearLeaderboards);
        Button buttonRemoveNonAdminUsers = findViewById(R.id.buttonRemoveNonAdminUsers);
        Button buttonCreateNewAdminUser = findViewById(R.id.buttonCreateNewAdminUser);
        Button buttonDeleteUser = findViewById(R.id.buttonDeleteUser);

        buttonClearLeaderboards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Need to implement logic to clear leaderboards
                Toast.makeText(admin_page.this, "Leaderboards cleared.", Toast.LENGTH_SHORT).show();
            }
        });

        buttonRemoveNonAdminUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Need to implement logic to be able to remove non-admin users
                Toast.makeText(admin_page.this, "Non-admin users removed.", Toast.LENGTH_SHORT).show();
            }
        });

        buttonCreateNewAdminUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newAdminUsername = editTextNewAdminUsername.getText().toString().trim();
                //Need to implement logic to create a new admin user with the String newAdminUsername
                Toast.makeText(admin_page.this, "New admin user created: " + newAdminUsername, Toast.LENGTH_SHORT).show();
            }
        });

        buttonDeleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userToDelete = editTextDeleteUser.getText().toString().trim();
                //Need to implement logic to delete the user with the String userToDelete
                Toast.makeText(admin_page.this, "User deleted: " + userToDelete, Toast.LENGTH_SHORT).show();
            }
        });
    }
}