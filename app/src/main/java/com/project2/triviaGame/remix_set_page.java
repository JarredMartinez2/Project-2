package com.project2.triviaGame;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.project2.triviaGame.Database.ProjectRepository;
import com.project2.triviaGame.Database.entities.Trivia;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
//TODO: make toast warning maybe
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
            List<Trivia> mcardSet1;
            @Override
            public void onClick(View v) {
                String cardSet1 = editTextCardSet1.getText().toString().trim();
                String cardSet2 = editTextCardSet2.getText().toString().trim();
                if (!cardSet1.isEmpty() || !cardSet2.isEmpty()) {
                    List<Trivia> mcardSet1 = repository.getAllTriviaLogs();
                    List<Trivia> mcardSet2 = new ArrayList<>();
                    for (int i = 0; i < mcardSet1.size(); i++) {
                        if (mcardSet1.get(i).getCategory().equals(cardSet1)) {
                            mcardSet2.add(mcardSet1.get(i));
                            if (mcardSet2.size() > 3) {
                                break;
                            }
                        }
                    }
                    for (int i = 0; i < mcardSet1.size(); i++) {
                        if (mcardSet1.get(i).getCategory().equals(cardSet2)) {
                            mcardSet2.add(mcardSet1.get(i));
                            if (mcardSet2.size() > 2) {
                                break;
                            }
                        }
                    }

                    Collections.shuffle(mcardSet2);
                    int setId = mcardSet1.get(mcardSet1.size() - 1).getSetId();

                    for (int i = 0; i < mcardSet2.size(); i++) {
                            mcardSet2.get(i).setCategory("remix of " + cardSet1+ " and " + cardSet2);
                            setId++;
                            mcardSet2.get(i).setSetId(setId);
                            repository.insertSet(mcardSet2.get(i));
                        }
                }
            }
        });
    }
}