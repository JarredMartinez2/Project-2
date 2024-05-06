package com.project2.triviaGame;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;

import com.project2.triviaGame.Database.ProjectRepository;
import com.project2.triviaGame.Database.entities.Trivia;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class question2_page extends AppCompatActivity {
    private long milliseconds = 15000;
    private ProjectRepository repository;
    private TextView timerView, questionView;
    private Button correct, wrong1, wrong2, wrong3, start;
    private List<Trivia> correctSet;
    private Intent intent;
    private int score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question2_page);
        repository = ProjectRepository.getRepository(getApplication());
        timerView = findViewById(R.id.timer_5);
        questionView = findViewById(R.id.question_5);
        correct = findViewById(R.id.correctAnswer_5);
        wrong1 = findViewById(R.id.incorrectAnswer1_5);
        wrong2 = findViewById(R.id.incorrectAnswer2_5);
        wrong3 = findViewById(R.id.incorrectAnswer3_5);
        correctSet = new ArrayList<>();
        score = getIntent().getIntExtra("score", 0);
        intent = new Intent(question2_page.this, question3.class);
        String name = getIntent().getStringExtra("nameOfSet");
        intent.putExtra("nameOfSet", name);
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
        questionView.setText(correctSet.get(1).getQuestion());
        correct.setText(correctSet.get(1).getCorrectAnswer());
        wrong1.setText(correctSet.get(1).getWrongAnswer());
        wrong2.setText(correctSet.get(1).getWrongAnswer2());
        wrong3.setText(correctSet.get(1).getWrongAnswer3());
        timer.start();

        correct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score += 1;
                intent.putExtra("score", score);
                timer.cancel();
                timer.onFinish();
            }
        });

        wrong1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.cancel();
                timer.onFinish();
            }
        });

        wrong2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.cancel();
                timer.onFinish();
            }
        });

        wrong3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.cancel();
                timer.onFinish();
            }
        });
    }

    CountDownTimer timer = new CountDownTimer(milliseconds, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            long seconds = (millisUntilFinished / 1000) % 60;
            String time = String.format(Locale.getDefault(), "%02d", seconds);
            timerView.setText(time);
            milliseconds = millisUntilFinished;
        }
        @Override
        public void onFinish() {
            timerView.setText("00");
            ViewCompat.setBackgroundTintList(wrong1, ContextCompat.getColorStateList(question2_page.this, R.color.red));
            ViewCompat.setBackgroundTintList(wrong2, ContextCompat.getColorStateList(question2_page.this, R.color.red));
            ViewCompat.setBackgroundTintList(wrong3, ContextCompat.getColorStateList(question2_page.this, R.color.red));
            ViewCompat.setBackgroundTintList(correct, ContextCompat.getColorStateList(question2_page.this, R.color.green));
            Toast.makeText(question2_page.this, "We made it here", Toast.LENGTH_SHORT).show();
            nextQuestion();
        }
    };

    public void nextQuestion() {
        Handler handler = new Handler();
        Runnable r=new Runnable() {
            public void run() {
                startActivity(intent);
            }
        };
        handler.postDelayed(r, 5000);
    }
}