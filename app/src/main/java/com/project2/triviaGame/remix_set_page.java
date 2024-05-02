package com.project2.triviaGame;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.project2.triviaGame.databinding.ActivityRemixSetPageBinding;

public class remix_set_page extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remix_set_page);
        EditText editTextCardSet1 = findViewById(R.id.CardSetEntry1);
        EditText editTextCardSet2 = findViewById(R.id.CardSetEntry2);
        Button RemixButton = findViewById(R.id.RemixButton);
        RemixButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cardSet1 = editTextCardSet1.getText().toString().trim();
                String cardSet2 = editTextCardSet1.getText().toString().trim();
            }
        });
    }
}