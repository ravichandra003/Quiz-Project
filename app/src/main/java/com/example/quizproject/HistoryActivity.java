package com.example.quizproject;

import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

public class HistoryActivity extends AppCompatActivity {

    private ListView historyListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        // Set up the history list view
        historyListView = findViewById(R.id.historyListView);

        // Here you can fetch and display the quiz history from Firebase or other storage.
    }
}
