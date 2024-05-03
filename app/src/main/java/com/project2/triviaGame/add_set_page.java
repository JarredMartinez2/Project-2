package com.project2.triviaGame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.project2.triviaGame.Database.ProjectRepository;
import com.project2.triviaGame.Database.entities.Trivia;

public class add_set_page extends AppCompatActivity {
    private ProjectRepository repository;
    private EditText question, category, correctAnswer, incorrectAnswer1, incorrectAnswer2, incorrectAnswer3;
    private Button submitQuestionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        repository = ProjectRepository.getRepository(getApplication());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_set_page);
        question = findViewById(R.id.mquestion);
        category = findViewById(R.id.category);
        correctAnswer = findViewById(R.id.correctAnswer1);
        incorrectAnswer1 = findViewById(R.id.incorrectAnswer1);
        incorrectAnswer2 = findViewById(R.id.incorrectAnswer2);
        incorrectAnswer3 = findViewById(R.id.incorrectAnswer3);
        submitQuestionButton = findViewById(R.id.submitQuestionButton);
        submitQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mquestion = question.getText().toString().trim();
                String mcategory = category.getText().toString().trim();
                String mcorrectAnswer = correctAnswer.getText().toString().trim();
                String mincorrectAnswer1 = incorrectAnswer1.getText().toString().trim();
                String mincorrectAnswer2 = incorrectAnswer2.getText().toString().trim();
                String mincorrectAnswer3 = incorrectAnswer3.getText().toString().trim();
                if (mquestion.isEmpty() || mcategory.isEmpty() || mcorrectAnswer.isEmpty()
                        || mincorrectAnswer1.isEmpty() || mincorrectAnswer2.isEmpty() || mincorrectAnswer3.isEmpty()) {
                    Toast.makeText(add_set_page.this, "All fields must be filled", Toast.LENGTH_SHORT).show();
                }
                else {
                    Trivia t = new Trivia(mcorrectAnswer, mincorrectAnswer1, mincorrectAnswer2, mincorrectAnswer3, mquestion, mcategory);
                    repository.insertSet(t);
                }
            }
        });
    }

}