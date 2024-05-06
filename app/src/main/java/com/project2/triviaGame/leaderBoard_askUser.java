package com.project2.triviaGame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.project2.triviaGame.Database.ProjectRepository;
import com.project2.triviaGame.Database.entities.LeaderBoard;

public class leaderBoard_askUser extends AppCompatActivity {
    private EditText userLBName;
    private Button continueButton;
    boolean debug = true;
    private ProjectRepository respository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_board_ask_user);
        int score = getIntent().getIntExtra("score", 0);
        userLBName = findViewById(R.id.lbName);
        continueButton = findViewById(R.id.displayLBbutton);
        respository = ProjectRepository.getRepository(getApplication());

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = userLBName.getText().toString().trim();
                if (name.length() <= 3) {
                    Intent intent = new Intent(leaderBoard_askUser.this, leaderboard_page.class);
                    respository.insertLB(new LeaderBoard(score, name));
                    startActivity(intent);
                } else {
                    Toast.makeText(leaderBoard_askUser.this, "user is greater than 3 characters!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}