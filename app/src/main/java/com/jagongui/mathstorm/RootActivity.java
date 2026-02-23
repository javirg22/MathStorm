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
            switch (a){
                case 1: raiz = 4;
                case 2: raiz = 9;
                case 3: raiz = 16;
                case 4: raiz = 25;
                case 5: raiz = 36;
                case 6: raiz = 48;
                case 7: raiz = 64;
                case 8: raiz = 81;
                case 9: raiz = 100;
            }



            correctAnswer = (int) Math.sqrt(raiz);
            tvOperation.setText("2√"+raiz+" = ?");
            etAnswer.setText("");
            tilAnswer.setError(null);
        } else {

            int a = new Random().nextInt(10) + 1;
            switch (a){
                case 1: raiz = 4;
                case 2: raiz = 9;
                case 3: raiz = 16;
                case 4: raiz = 25;
                case 5: raiz = 36;
                case 6: raiz = 48;
                case 7: raiz = 64;
                case 8: raiz = 81;
                case 9: raiz = 100;
            }
            int b = new Random().nextInt(10) + 1;
            switch (b){
                case 1: raiz2 = 4;
                case 2: raiz2 = 9;
                case 3: raiz2 = 16;
                case 4: raiz2 = 25;
                case 5: raiz2 = 36;
                case 6: raiz2 = 48;
                case 7: raiz2 = 64;
                case 8: raiz2 = 81;
                case 9: raiz2 = 100;
            }

            correctAnswer = (int) (Math.sqrt(raiz) + Math.sqrt(raiz2));
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