package com.example.quizproject;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;

public class ScoreSummaryActivity extends AppCompatActivity {
    private TextView scoreTextView;
    private Button playAgainButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_summary);

        scoreTextView = findViewById(R.id.scoreTextView);
        playAgainButton = findViewById(R.id.playAgainButton);

        // Get the score and total questions from the Intent
        int score = getIntent().getIntExtra("score", 0);
        int totalQuestions = getIntent().getIntExtra("totalQuestions", 0);

        scoreTextView.setText("Your Score: " + score + "/" + totalQuestions);

        playAgainButton.setOnClickListener(v -> {
            // Redirect to the quiz activity
            Intent intent = new Intent(ScoreSummaryActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }
}
