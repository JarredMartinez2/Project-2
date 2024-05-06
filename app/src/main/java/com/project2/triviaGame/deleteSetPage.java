package com.project2.triviaGame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.project2.triviaGame.Database.ProjectRepository;
import com.project2.triviaGame.Database.entities.Trivia;
import java.util.ArrayList;
import java.util.List;

public class deleteSetPage extends AppCompatActivity {

    private ProjectRepository repository;
    private TextView nameOfSets;
    private EditText setName;
    private Button deleteButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_set_page);
        repository = ProjectRepository.getRepository(getApplication());
        deleteButton = findViewById(R.id.deletebutton);
        setName = findViewById(R.id.nameOfSetToDelete);
        ArrayList<Trivia> allSets;
        if (repository != null) {
            allSets = repository.getAllTriviaLogs();
        } else {
            allSets = new ArrayList<>();
        }
        nameOfSets = findViewById(R.id.setNames);
        String SetNames = "";
        for(int i = 0; i < allSets.size(); i=i+5) {
            if (allSets.size() == 5) {
                SetNames = allSets.get(i).getCategory();
                break;
            }
            SetNames += allSets.get(i).getCategory() + "\n";
        }
        nameOfSets.setText(SetNames);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = setName.getText().toString().trim();
                if (!name.isEmpty()) {
                    List<Trivia> set = repository.getAllTriviaLogs();
                    for(int i = 0; i < set.size(); i++) {
                        if (set.get(i).getCategory().equals(name)) {
                            repository.deleteSet(set.get(i));
                        }
                    }
                }
            }
        });
    }
}