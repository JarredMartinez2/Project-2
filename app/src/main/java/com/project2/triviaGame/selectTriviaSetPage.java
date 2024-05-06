package com.project2.triviaGame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.project2.triviaGame.Database.ProjectRepository;
import com.project2.triviaGame.Database.entities.Trivia;

import java.util.ArrayList;

public class selectTriviaSetPage extends AppCompatActivity {

    private ProjectRepository repository;
    private EditText setName;
    private TextView displayingSets;
    private Button goOn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_trivia_set_page);
        repository = ProjectRepository.getRepository(getApplication());
        setName = findViewById(R.id.setChosenToPlay);
        goOn = findViewById(R.id.continueButton);
        ArrayList<Trivia> allSets;
        if (repository != null) {
            allSets = repository.getAllTriviaLogs();
        } else {
            allSets = new ArrayList<>();
        }
        displayingSets = findViewById(R.id.displayingSets);
        String SetNames = "";
        for(int i = 0; i < allSets.size(); i=i+5) {
            if (allSets.size() == 5) {
                SetNames = allSets.get(i).getCategory();
                break;
            }
            SetNames += allSets.get(i).getCategory() + "\n";
        }
        displayingSets.setText(SetNames);
        goOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(selectTriviaSetPage.this, play_page.class);
                String name = setName.getText().toString().trim();
                intent.putExtra("nameOfSet", name);
                startActivity(intent);
            }
        });
    }
}