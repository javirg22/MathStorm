package com.jagongui.mathstorm;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EjerciciosActivity extends AppCompatActivity {


    CardView sumaCv;
    CardView restaCv;
    CardView multiCv;
    CardView divCv;
    CardView rootCv;
    CardView expCv;
    FloatingActionButton fab;

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

        fab = findViewById(R.id.fab);
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
        BottomAppBar bottomAppBar = findViewById(R.id.bottom_app_bar);

        rootCv.setOnClickListener(v -> {
            Intent intent = new Intent(EjerciciosActivity.this, RootActivity.class);
            startActivity(intent);
        });

        expCv.setOnClickListener(v -> {
            Intent intent = new Intent(EjerciciosActivity.this, ExpActivity.class);
            startActivity(intent);
        });

        bottomAppBar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.menu_perfil) {
                Intent intent = new Intent(EjerciciosActivity.this, perfil.class);
                startActivity(intent);
                return true;
            }
            return false;
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheetDialog();
            }
        });
    }
    private void showBottomSheetDialog () {
        View view = LayoutInflater.from(this).inflate(R.layout.bottom_sheet_layout, null);



        // Crear el BottomSheetDialog
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.show();

        TextView option1 = view.findViewById(R.id.OpcionEjercicios);
        TextView option2 = view.findViewById(R.id.OpcionEjemplos);
        TextView option3 = view.findViewById(R.id.OpcionPaginaPrincipal);

        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(EjerciciosActivity.this, getString(R.string.Yaestas), Toast.LENGTH_SHORT).show();
            }
        });

        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EjerciciosActivity.this, ExamplesActivity.class));
            }
        });
        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EjerciciosActivity.this, MainActivity.class));
            }
        });
    }
}