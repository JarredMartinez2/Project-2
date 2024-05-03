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

import com.project2.triviaGame.Database.ProjectRepository;
import com.project2.triviaGame.Database.entities.Trivia;
import com.project2.triviaGame.databinding.ActivityRemixSetPageBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class remix_set_page extends AppCompatActivity {
    private ProjectRepository repository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        repository = ProjectRepository.getRepository(getApplication());
        setContentView(R.layout.activity_remix_set_page);
        EditText editTextCardSet1 = findViewById(R.id.CardSetEntry1);
        EditText editTextCardSet2 = findViewById(R.id.CardSetEntry2);
        Button RemixButton = findViewById(R.id.RemixButton);
        RemixButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cardSet1 = editTextCardSet1.getText().toString().trim();
                String cardSet2 = editTextCardSet1.getText().toString().trim();
                if (!cardSet1.isEmpty() || !cardSet2.isEmpty()) {
                    List<Trivia> mcardSet1 = repository.getCurrentSet(cardSet1);
                    List<Trivia> mcardSet2 = repository.getCurrentSet(cardSet2);
                    if (!mcardSet1.isEmpty() || !mcardSet2.isEmpty()) {
                        List<Trivia> remixSet = new ArrayList<>();
                        for (int i = 0; i <= 8; i= i+2) {
                            remixSet.add(mcardSet1.get(i));
                        }
                        for (int i = 1; i <= 9; i = i+2) {
                            remixSet.add(mcardSet2.get(i));
                        }
                        Collections.shuffle(remixSet);

                        for (int i = 0; i < 10; i++) {
                            remixSet.get(i).setCategory("remix of " + cardSet1+ " and " + cardSet2);
                            repository.insertSet(remixSet.get(i));
                        }
                    }

                }
            }

        });
    }
}