package com.project2.triviaGame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.project2.triviaGame.Database.Project2Database;
import com.project2.triviaGame.Database.ProjectRepository;
import com.project2.triviaGame.Database.entities.Trivia;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class deleteSetPage extends AppCompatActivity {

    private ProjectRepository repository;
    private TextView nameOfSets;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_set_page);
        repository = ProjectRepository.getRepository(getApplication());

        ArrayList<Trivia> allSets;
        if (repository != null) {
            allSets = repository.getAllTriviaLogs();
        } else {
            allSets = new ArrayList<>();
        }
        nameOfSets = findViewById(R.id.setNames);
        String SetNames = "";
        for(int i = 0; i < allSets.size(); i++) {
            SetNames = allSets.get(i).getCategory() + "\n";
        }
        nameOfSets.setText(SetNames);
    }
}