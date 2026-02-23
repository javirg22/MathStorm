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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ExamplesActivity extends AppCompatActivity {


    CardView sumaCv;
    CardView restaCv;
    CardView multiCv;
    CardView divCv;
    FirebaseFirestore db;
    String userId;
    FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_examples);

        sumaCv = findViewById(R.id.SumaCv);
        restaCv = findViewById(R.id.RestaCv);
        multiCv = findViewById(R.id.MultiCv);
        divCv = findViewById(R.id.DivsCv);
        fab = findViewById(R.id.fab);
        db = FirebaseFirestore.getInstance();
        userId = FirebaseAuth.getInstance().getUid();
        sumaCv.setOnClickListener(v -> {
            saveLastExample("suma");
            startActivity(new Intent(ExamplesActivity.this, SumaExample.class));
        });

        restaCv.setOnClickListener(v -> {
            saveLastExample("resta");
            startActivity( new Intent(ExamplesActivity.this, activity_resta_example.class));
        });

        multiCv.setOnClickListener(v -> {
            saveLastExample("multiplicacion");
            startActivity(new Intent(ExamplesActivity.this, activity_multi_example.class));
        });

        divCv.setOnClickListener(v -> {
            saveLastExample("division");
            startActivity(new Intent(ExamplesActivity.this, activity_divs_example.class));
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheetDialog();
            }
        });
        BottomAppBar bottomAppBar = findViewById(R.id.bottom_app_bar);

        bottomAppBar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.menu_perfil) {
                Intent intent = new Intent(ExamplesActivity.this, perfil.class);
                startActivity(intent);
                return true;
            }
            return false;
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
                startActivity(new Intent(ExamplesActivity.this, EjerciciosActivity.class));
            }
        });

        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText( ExamplesActivity.this, getString(R.string.Yaestas) , Toast.LENGTH_SHORT).show();
            }
        });
        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ExamplesActivity.this, MainActivity.class));
            }
        });
    }
    private void saveLastExample(String exampleName) {
        if (userId == null) return;

        db.collection("user")
                .document(userId)
                .update("lastExampleViewed", exampleName);
    }
}