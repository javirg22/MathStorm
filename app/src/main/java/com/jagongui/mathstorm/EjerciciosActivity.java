package com.jagongui.mathstorm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EjerciciosActivity extends AppCompatActivity {


    CardView sumaCv;
    CardView restaCv;
    CardView multiCv;
    CardView divCv;
    CardView rootCv;
    CardView expCv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ejercicios);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        sumaCv = findViewById(R.id.SumaCv);
        restaCv = findViewById(R.id.RestaCv);
        multiCv = findViewById(R.id.MultiCv);
        divCv = findViewById(R.id.DivsCv);
        rootCv = findViewById(R.id.RootCv);
        expCv = findViewById(R.id.ExpCv);

        sumaCv.setOnClickListener(v -> {
            Intent intent = new Intent(EjerciciosActivity.this, SumaActivity.class);
            startActivity(intent);
        });

        restaCv.setOnClickListener(v -> {
            Intent intent = new Intent(EjerciciosActivity.this, RestaActivity.class);
            startActivity(intent);
        });

        multiCv.setOnClickListener(v -> {
            Intent intent = new Intent(EjerciciosActivity.this, MultiActivity.class);
            startActivity(intent);
        });

        divCv.setOnClickListener(v -> {
            Intent intent = new Intent(EjerciciosActivity.this, DivsActivity.class);
            startActivity(intent);
        });

        rootCv.setOnClickListener(v -> {
            Intent intent = new Intent(EjerciciosActivity.this, DivsActivity.class);
            startActivity(intent);
        });

        expCv.setOnClickListener(v -> {
            Intent intent = new Intent(EjerciciosActivity.this, DivsActivity.class);
            startActivity(intent);
        });


    }
}