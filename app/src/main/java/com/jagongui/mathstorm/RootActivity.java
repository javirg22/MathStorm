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

public class RootActivity extends AppCompatActivity {

    private TextView tvOperation;
    private TextInputEditText etAnswer;
    private TextInputLayout tilAnswer;

    private int correctAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suma);

        tvOperation = findViewById(R.id.tvOperation);
        etAnswer = findViewById(R.id.etAnswer);
        tilAnswer = findViewById(R.id.tilAnswer);

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
        int nTerm = new Random().nextInt(2) + 2;
        int raiz = 0;
        int raiz2 = 0;
        if(nTerm == 2) {

            int a = new Random().nextInt(10) + 1;
            raiz = a * a;



            correctAnswer = a;
            tvOperation.setText("2√"+raiz+" = ?");
            etAnswer.setText("");
            tilAnswer.setError(null);
        } else {

            int a = new Random().nextInt(10) + 1;
            raiz = a * a;
            int b = new Random().nextInt(9) + 2;
            raiz2 = b * b;

            correctAnswer = a + b;
            tvOperation.setText("2√"+raiz+" + 2√"+raiz2+" = ?");
            etAnswer.setText("");
            tilAnswer.setError(null);
        }

    }

    private void checkAnswer() {
        String userAnswer = etAnswer.getText() != null
                ? etAnswer.getText().toString()
                : "";

        if (userAnswer.isEmpty()) {
            tilAnswer.setError("Introduce una respuesta");
            return;
        }

        if (Integer.parseInt(userAnswer) == correctAnswer) {
            tilAnswer.setError(null);
            Toast.makeText(this, "✅ Correcto", Toast.LENGTH_SHORT).show();
        } else {
            tilAnswer.setError("❌ Incorrecto");
        }
    }
}