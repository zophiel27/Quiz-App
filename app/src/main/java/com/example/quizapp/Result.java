package com.example.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;

import java.util.concurrent.Executor;
import java.util.function.IntConsumer;

public class Result extends AppCompatActivity {

    String name;
    int score;
    Button btnBack, btnShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        init();

        btnBack.setOnClickListener(v -> {
            Intent i = new Intent(Result.this, Home.class);
            startActivity(i);
            finish();
        });

        btnShare.setOnClickListener(v -> {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, name + " scored " + score + "/10 in the Quiz App!");
            startActivity(Intent.createChooser(shareIntent, "Share via"));
        });
    }
    private void init(){
        name = getIntent().getStringExtra("key_name");
        score = getIntent().getIntExtra("key_score", 0);

        btnBack = findViewById(R.id.btnBack);
        btnShare = findViewById(R.id.btnShare);

        TextView scoreText = findViewById(R.id.tvScore);
        TextView nameText = findViewById(R.id.tvName);
        nameText.setText(name);
        scoreText.setText((score + "/10"));
    }
}

