package com.project2.triviaGame;

import androidx.appcompat.app.AppCompatActivity;
import android.os.CountDownTimer;
import android.os.Bundle;

public class play_page extends AppCompatActivity {
    private long timeLeftInMillis = 10000; //10second timer
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_page);
    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000);
    }
}