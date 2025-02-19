package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class Home extends AppCompatActivity {

    Button startBtn;
    EditText nameEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        init();

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEt.getText().toString();
                if (name.matches("")){
                    Toast.makeText(Home.this, "Name cannot be empty!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent i = new Intent(Home.this, Quiz.class);
                    i.putExtra("key_name", name);
                    startActivity(i);
                    finish();
                }
            }
        });
    }
    private void init(){
        startBtn = findViewById(R.id.btnStart);
        nameEt = findViewById(R.id.etName);
    }
}