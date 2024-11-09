package com.example.quizproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up the Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Get card views using the correct IDs
        CardView natureCard = findViewById(R.id.card_nature);
        CardView scienceCard = findViewById(R.id.card_science);
        CardView computerScienceCard = findViewById(R.id.card_computerscience);

        // Set onClick listeners for each card
        natureCard.setOnClickListener(v -> openQuizActivity("Nature"));
        scienceCard.setOnClickListener(v -> openQuizActivity("Science"));
        computerScienceCard.setOnClickListener(v -> openQuizActivity("Computer Science"));
    }

    // Inflate the menu to add the "History" button
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    // Handle clicks on the menu items
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_history) {
            // Navigate to the history activity
            Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Open the quiz activity for the selected subject
    private void openQuizActivity(String subject) {
        Intent intent = new Intent(MainActivity.this, QuizActivity.class);
        intent.putExtra("subject", subject);
        startActivity(intent); // Launch QuizActivity
    }
}
