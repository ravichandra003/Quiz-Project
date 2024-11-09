package com.example.quizproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuizActivity extends AppCompatActivity {
    private FirebaseFirestore db;
    private List<Question> questionsList = new ArrayList<>();
    private int currentQuestionIndex = 0;
    private TextView questionTextView;
    private RadioGroup optionsRadioGroup;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // Initialize Firestore
        db = FirebaseFirestore.getInstance();

        // Get views by their IDs
        questionTextView = findViewById(R.id.questionTextView);
        optionsRadioGroup = findViewById(R.id.optionsRadioGroup);

        // Fetch questions from Firestore
        fetchRandomQuestionsFromFirestore();
    }

    private void fetchRandomQuestionsFromFirestore() {
        db.collection("questions")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        QuerySnapshot snapshot = task.getResult();
                        if (snapshot != null) {
                            for (QueryDocumentSnapshot document : snapshot) {
                                String questionText = document.getString("question");
                                String option1 = document.getString("option1");
                                String option2 = document.getString("option2");
                                String option3 = document.getString("option3");
                                String option4 = document.getString("option4");
                                String correctAnswer = document.getString("correctAnswer");

                                Question question = new Question(questionText, option1, option2, option3, option4, correctAnswer);
                                questionsList.add(question);
                            }

                            // Now select 5 random questions
                            Random random = new Random();
                            List<Question> randomQuestions = new ArrayList<>();
                            while (randomQuestions.size() < 5 && questionsList.size() > 0) {
                                int index = random.nextInt(questionsList.size());
                                randomQuestions.add(questionsList.get(index));
                                questionsList.remove(index);
                            }

                            // Load the first question
                            loadQuestion(randomQuestions.get(currentQuestionIndex));
                        }
                    } else {
                        Toast.makeText(this, "Failed to load questions", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void loadQuestion(Question question) {
        questionTextView.setText(question.getQuestion());
        ((RadioButton) findViewById(R.id.option1)).setText(question.getOption1());
        ((RadioButton) findViewById(R.id.option2)).setText(question.getOption2());
        ((RadioButton) findViewById(R.id.option3)).setText(question.getOption3());
        ((RadioButton) findViewById(R.id.option4)).setText(question.getOption4());

        // Set the onClick listener for the submit button
        findViewById(R.id.submitButton).setOnClickListener(v -> checkAnswer(question));
    }

    private void checkAnswer(Question question) {
        int selectedId = optionsRadioGroup.getCheckedRadioButtonId();
        RadioButton selectedOption = findViewById(selectedId);

        if (selectedOption != null && selectedOption.getText().toString().equals(question.getCorrectAnswer())) {
            score++;
        }

        // Move to the next question or show results
        if (currentQuestionIndex < 4) {
            currentQuestionIndex++;
            loadQuestion(questionsList.get(currentQuestionIndex));
        } else {
            // Quiz finished, show score summary
            showScoreSummary();
        }
    }

    private void showScoreSummary() {
        Intent intent = new Intent(QuizActivity.this, ScoreSummaryActivity.class);
        intent.putExtra("score", score);
        intent.putExtra("totalQuestions", 5);
        startActivity(intent);
    }
}
