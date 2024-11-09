package com.example.quizproject;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get card views using the correct IDs
        CardView natureCard = findViewById(R.id.card_nature);
        CardView scienceCard = findViewById(R.id.card_science);
        CardView computerScienceCard = findViewById(R.id.card_computerscience);

        // Set onClick listeners for each card
        natureCard.setOnClickListener(v -> openQuizActivity("Nature"));
        scienceCard.setOnClickListener(v -> openQuizActivity("Science"));
        computerScienceCard.setOnClickListener(v -> openQuizActivity("Computer Science"));
    }

    // Open the quiz activity for the selected subject
    private void openQuizActivity(String subject) {
        Intent intent = new Intent(MainActivity.this, QuizActivity.class);
        intent.putExtra("subject", subject);
        startActivity(intent); // Launch QuizActivity
    }
}
