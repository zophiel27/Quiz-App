package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Quiz extends AppCompatActivity {

    TextView questionText;
    TextView tv_question_number;
    RadioGroup rgOptions;
    Button nextButton, prevButton;
    String name;

    String[] questions = {
            "What is 5 + 3?",
            "What is 10 - 4?",
            "What is 6 × 2?",
            "What is 16 ÷ 4?",
            "What is 9 + 6?",
            "What is 8 - 5?",
            "What is 3 × 3?",
            "What is 12 ÷ 3?",
            "What is 7 + 2?",
            "What is 15 - 7?"
    };

    String[][] options = {
            {"6", "7", "8", "9"},
            {"4", "5", "6", "10"},
            {"10", "11", "12", "14"},
            {"2", "4", "3",  "5"},
            {"13", "14", "15", "16"},
            {"3", "2", "2", "4"},
            {"6", "7", "8", "9"},
            {"3", "4", "5", "6"},
            {"7", "8", "9", "10"},
            {"6", "8", "7", "9"}
    };

    int[] correctAnswers = {2, 2, 2, 1, 2, 0, 3, 1, 2, 1};

    int currentQuestion = 0;
    int score = 0;
    int[] userAnswers = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        init();

        prevButton.setVisibility(View.GONE);
        updateQuestion();

        nextButton.setOnClickListener(v -> {
            saveAnswer();
            if (currentQuestion < questions.length - 1) {
                currentQuestion++;
                updateQuestion();
                prevButton.setVisibility(View.VISIBLE);
            } else {
                calculateScore();
                Intent intent = new Intent(Quiz.this, Result.class);
                intent.putExtra("key_name", name);
                intent.putExtra("key_score", score);
                startActivity(intent);
                finish();
                Toast.makeText(this, score, Toast.LENGTH_SHORT).show();
            }
        });

        prevButton.setOnClickListener(v -> {
            saveAnswer();
            if (currentQuestion > 0) {
                currentQuestion--;
                updateQuestion();
                if (currentQuestion == 0) {
                    prevButton.setVisibility(View.GONE);
                }
            }
        });
    }
    private void init() {
        name = getIntent().getStringExtra("key_name");

        questionText = findViewById(R.id.tv_question);
        rgOptions = findViewById(R.id.radioGroupOptions);
        nextButton = findViewById(R.id.btnNext);
        prevButton = findViewById(R.id.btnPrev);
        tv_question_number = findViewById((R.id.tv_question_number));
    }
    void updateQuestion() {
        questionText.setText(questions[currentQuestion]);
        tv_question_number.setText((currentQuestion+1)+"/10");
        rgOptions.clearCheck();
        for (int i = 0; i < options[currentQuestion].length; i++) {
            ((RadioButton) rgOptions.getChildAt(i)).setText(options[currentQuestion][i]);
        }
    }
    void saveAnswer() {
        int selectedId = rgOptions.getCheckedRadioButtonId();
        if (selectedId != -1) {
            int selectedIndex = rgOptions.indexOfChild(findViewById(selectedId));
            userAnswers[currentQuestion] = selectedIndex;
        }
    }
    void calculateScore() {
        score = 0;
        for (int i = 0; i < questions.length; i++) {
            if (userAnswers[i] == correctAnswers[i]) {
                score++;
            }
        }
    }
}
