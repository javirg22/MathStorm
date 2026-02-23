package com.jagongui.mathstorm;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
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
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    CardView btn_exc;
    CardView btn_exm;

    FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EdgeToEdge.enable(this);

        mAuth = FirebaseAuth.getInstance();
        btn_exc = findViewById(R.id.ExerciseButton);
        btn_exm = findViewById(R.id.ExamplesButton);
        fab = findViewById(R.id.fab);

        btn_exc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, EjerciciosActivity.class));
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheetDialog();
            }
        });
        btn_exm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ExamplesActivity.class));
            }
        });

        BottomAppBar bottomAppBar = findViewById(R.id.bottom_app_bar);
        bottomAppBar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.menu_perfil) {
                Intent intent = new Intent(MainActivity.this, perfil.class);
                startActivity(intent);
                return true;
            }
            return false;
        });
    }
    public void openPrueba(View v){
        Intent intent = new Intent(MainActivity.this, EjerciciosActivity.class);
        startActivity(intent);
    }
    public void openExamples(View v){
        Intent intent = new Intent(MainActivity.this, ExamplesActivity.class);
        startActivity(intent);
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.bottom_app_bar_menu, menu);
    }

    public void showBottomSheetDialog() {
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
                startActivity(new Intent(MainActivity.this, EjerciciosActivity.class));
            }
        });

        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ExamplesActivity.class));
            }
        });
        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText( MainActivity.this, getString(R.string.Yaestas) , Toast.LENGTH_SHORT).show();
            }
        });
    }
}