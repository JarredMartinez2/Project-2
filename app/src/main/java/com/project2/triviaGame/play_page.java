package com.project2.triviaGame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;

import android.content.res.ColorStateList;
import android.os.CountDownTimer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.project2.triviaGame.Database.ProjectRepository;
import com.project2.triviaGame.Database.entities.Trivia;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class play_page extends AppCompatActivity {
    private ProjectRepository repository;
    private TextView timerView, questionView;
    private Button correct, wrong1, wrong2, wrong3, start;
    private int count = 0;
    private List<Trivia> correctSet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_page);
        repository = ProjectRepository.getRepository(getApplication());
        timerView = findViewById(R.id.timer);
        questionView = findViewById(R.id.question);
        correct = findViewById(R.id.correctAnswer);
        wrong1 = findViewById(R.id.incorrectAnswer1);
        wrong2 = findViewById(R.id.incorrectAnswer2);
        wrong3 = findViewById(R.id.incorrectAnswer3);
        start = findViewById(R.id.startButton);
        correctSet = new ArrayList<>();

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = getIntent().getStringExtra("nameOfSet");
                List<Trivia> fullset = repository.getAllTriviaLogs();
                if (name == null) {
                    name = "";
                }
                if (!name.isEmpty()) {
                    for(int i = 0; i < fullset.size(); i++) {
                        if (fullset.get(i).getCategory().equals(name)) {
                            correctSet.add(fullset.get(i));
                        }
                    }
                }
                start.setVisibility(View.INVISIBLE);
                timerView.setVisibility(View.VISIBLE);
                questionView.setVisibility(View.VISIBLE);
                correct.setVisibility(View.VISIBLE);
                wrong1.setVisibility(View.VISIBLE);
                wrong2.setVisibility(View.VISIBLE);
                wrong3.setVisibility(View.VISIBLE);

                questionView.setText(correctSet.get(count).getQuestion());
                correct.setText(correctSet.get(count).getCorrectAnswer());
                wrong1.setText(correctSet.get(count).getWrongAnswer());
                wrong2.setText(correctSet.get(count).getWrongAnswer2());
                wrong3.setText(correctSet.get(count).getWrongAnswer3());
                timer.start();
            }
        });
    }

    CountDownTimer timer = new CountDownTimer(15000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            long seconds = (millisUntilFinished / 1000) % 60;
            String time = String.format(Locale.getDefault(), "%02d", seconds);
            timerView.setText(time);
        }
       @Override
       public void onFinish() {
            count++;
           timerView.setText("");
           ViewCompat.setBackgroundTintList(wrong1, ContextCompat.getColorStateList(play_page.this, R.color.red));
           ViewCompat.setBackgroundTintList(wrong2, ContextCompat.getColorStateList(play_page.this, R.color.red));
           ViewCompat.setBackgroundTintList(wrong3, ContextCompat.getColorStateList(play_page.this, R.color.red));
           ViewCompat.setBackgroundTintList(correct, ContextCompat.getColorStateList(play_page.this, R.color.green));
           try {
               play_page.this.wait(5000);
           } catch (InterruptedException e) {
               throw new RuntimeException(e);
           }
           while (count < correctSet.size()) {
               ViewCompat.setBackgroundTintList(wrong1, ContextCompat.getColorStateList(play_page.this, R.color.purple));
               ViewCompat.setBackgroundTintList(wrong2, ContextCompat.getColorStateList(play_page.this, R.color.purple));
               ViewCompat.setBackgroundTintList(wrong3, ContextCompat.getColorStateList(play_page.this, R.color.purple));
               ViewCompat.setBackgroundTintList(correct, ContextCompat.getColorStateList(play_page.this, R.color.purple));
               questionView.setText(correctSet.get(count).getQuestion());
               correct.setText(correctSet.get(count).getCorrectAnswer());
               wrong1.setText(correctSet.get(count).getWrongAnswer());
               wrong2.setText(correctSet.get(count).getWrongAnswer2());
               wrong3.setText(correctSet.get(count).getWrongAnswer3());
               timer.start();
           }

       }
       };

}