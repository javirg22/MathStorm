package com.jagongui.mathstorm;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Random;

public class DivsActivity extends AppCompatActivity {

    private TextView tvOperation;
    private TextInputEditText etAnswer;
    private TextInputEditText etAnswerR;
    private TextInputLayout tilAnswer;
    private TextInputLayout tilAnswerR;

    private int correctAnswerC;
    private int correctAnswerR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_divs);

        tvOperation = findViewById(R.id.tvOperation);
        etAnswer = findViewById(R.id.etAnswer);
        etAnswerR = findViewById(R.id.etAnswerResto);
        tilAnswer = findViewById(R.id.tilAnswer);
        tilAnswerR = findViewById(R.id.tilAnswerResto);

        Button btnCheck = findViewById(R.id.btnCheck);
        Button btnNew = findViewById(R.id.btnNew);

        generateNewQuestion();

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
            }
        });

        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateNewQuestion();
            }
        });

    }

    private void generateNewQuestion() {


            int a = new Random().nextInt(50) + 1;
            int b = new Random().nextInt(9) + 2;



            correctAnswerC = a / b;
            correctAnswerR = a % b;
            tvOperation.setText(a + " / " + b + " = ?");
            etAnswer.setText("");
            etAnswerR.setText("");
            tilAnswer.setError(null);
            tilAnswerR.setError(null);

    }

    private void checkAnswer() {
        String userAnswer = etAnswer.getText() != null
                ? etAnswer.getText().toString()
                : "";

        String userAnswerResto = etAnswerR.getText() != null
                ? etAnswerR.getText().toString()
                : "";

        if (userAnswer.isEmpty()) {
            tilAnswer.setError("Introduce una respuesta");
            return;
        }

        if (Integer.parseInt(userAnswer) == correctAnswerC  && Integer.parseInt(userAnswerResto) == correctAnswerR) {
            tilAnswer.setError(null);
            tilAnswerR.setError(null);
            Toast.makeText(this, "✅ Correcto", Toast.LENGTH_SHORT).show();
        } else {
            if (Integer.parseInt(userAnswer) != correctAnswerC) {
                tilAnswer.setError("❌ Incorrecto");
            }
            if(Integer.parseInt(userAnswerResto) != correctAnswerR) {
                tilAnswerR.setError("❌ Incorrecto");
            }

            if (Integer.parseInt(userAnswer) == correctAnswerC) {
                tilAnswer.setError(null);
            }
            if(Integer.parseInt(userAnswerResto) == correctAnswerR) {
                tilAnswerR.setError(null);
            }

        }
    }
}
