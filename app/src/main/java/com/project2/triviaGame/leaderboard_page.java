package com.project2.triviaGame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.project2.triviaGame.Database.ProjectRepository;
import com.project2.triviaGame.Database.entities.LeaderBoard;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class leaderboard_page extends AppCompatActivity {
    private ProjectRepository repository;
    private TextView lbSlot1, lbSlot2, lbSlot3;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard_page);
        repository = ProjectRepository.getRepository(getApplication());
        lbSlot1 = findViewById(R.id.textViewLeaderboard1);
        lbSlot2 = findViewById(R.id.textViewLeaderboard2);
        lbSlot3 = findViewById(R.id.textViewLeaderboard3);
        button = findViewById(R.id.backToLandingPage);
        ArrayList<LeaderBoard> leaderBoards = (ArrayList<LeaderBoard>) repository.getALlScores();
        ArrayList<Integer> scores = new ArrayList<>();
        ArrayList<LeaderBoard> sortedbyScore = new ArrayList<>();
        scores.add(0);
        scores.add(1);
        scores.add(2);
        scores.add(3);
        scores.add(4);
        scores.add(5);
        for (int i = scores.size() - 1; i >= 0; i--) {
            for (int k = 0; k < leaderBoards.size(); k++) {
                if ((int) scores.get(i) == leaderBoards.get(k).getScore()) {
                    if (sortedbyScore.size() < 3) {
                        sortedbyScore.add(leaderBoards.get(k));
                    } else {
                        break;
                    }
                }
            }
        }
        if (sortedbyScore.size() <= 1) {
            lbSlot1.setText("1 " + sortedbyScore.get(0).getLbName() + " " + sortedbyScore.get(0).getScore());
        }
        if (sortedbyScore.size() <= 2) {
            lbSlot1.setText("1 " + sortedbyScore.get(0).getLbName() + " " + sortedbyScore.get(0).getScore());
            lbSlot2.setText("2 " + sortedbyScore.get(1).getLbName() + " " + sortedbyScore.get(1).getScore());
        }
        if(sortedbyScore.size() >= 3) {
            lbSlot1.setText("1 " + sortedbyScore.get(0).getLbName() + " " + sortedbyScore.get(0).getScore());
            lbSlot2.setText("2 " + sortedbyScore.get(1).getLbName() + " " + sortedbyScore.get(1).getScore());
            lbSlot2.setText("3 " + sortedbyScore.get(2).getLbName() + " " + sortedbyScore.get(2).getScore());
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(leaderboard_page.this, landing_page.class));
            }
        });
    }
}