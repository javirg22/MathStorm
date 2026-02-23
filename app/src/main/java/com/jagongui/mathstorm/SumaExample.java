package com.jagongui.mathstorm;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SumaExample extends AppCompatActivity {
    FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_suma_example);
        BottomAppBar bottomAppBar = findViewById(R.id.bottom_app_bar);
        fab = findViewById(R.id.fab);
        bottomAppBar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.menu_perfil) {
                startActivity(new Intent(SumaExample.this, perfil.class));
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
    public void showBottomSheetDialog(){
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
                startActivity(new Intent(SumaExample.this, EjerciciosActivity.class));
            }
        });

        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SumaExample.this, ExamplesActivity.class));
            }
        });
        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SumaExample.this, MainActivity.class));
            }
        });
    }
}