package com.jagongui.mathstorm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ImageView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;

public class perfil extends AppCompatActivity {
    Button exit;
    FirebaseAuth mAuth;
    FirebaseFirestore db;
    TextView tvName, tvEmail, tvRacha, tvEjercicios, tvEjemplo;
    ImageView imgFuego;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        exit = findViewById(R.id.buttonLogOut);
        tvName = findViewById(R.id.User);
        tvEmail = findViewById(R.id.Email);
        tvRacha = findViewById(R.id.racha);
        tvEjercicios = findViewById(R.id.EjemploC);
        tvEjemplo = findViewById(R.id.EjemploV);
        imgFuego = findViewById(R.id.fuego);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        loadUserData();
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                finish();
                startActivity( new Intent(perfil.this, login.class));
            }
        });

    }
    private void loadUserData() {
        String uid = mAuth.getCurrentUser().getUid();

        db.collection("user")
                .document(uid)
                .get()
                .addOnSuccessListener(document -> {
                    if (document.exists()) {

                        String name = document.getString("name");
                        String email = document.getString("email");
                        Long streak = document.getLong("streak");
                        Long ejercicios = document.getLong("exercisesCompleted");
                        String lastExample = document.getString("lastExampleViewed");
                        Long rachaActual = streak != null ? streak : 0;

                        tvName.setText(name != null ? name : "Sin nombre");
                        tvEmail.setText(email != null ? email : "");

                        tvRacha.setText((streak != null ? streak : 0) +""+ getString(R.string.dias));

                        if (rachaActual > 0) {
                            imgFuego.setColorFilter(
                                    getResources().getColor(R.color.rojo, null)
                            );
                        } else {
                            imgFuego.clearColorFilter();
                        }
                        tvEjercicios.setText(
                                String.valueOf(ejercicios != null ? ejercicios : 0)
                        );
                        if (lastExample != null) {
                            switch (lastExample) {
                                case "suma":
                                    tvEjemplo.setText(getString(R.string.ultimoVistoSuma));
                                    break;
                                case "resta":
                                    tvEjemplo.setText(getString(R.string.ultimoVistoResta));
                                    break;
                                case "multiplicacion":
                                    tvEjemplo.setText(R.string.ultimoVistoMultiplicacion);
                                    break;
                                case "division":
                                    tvEjemplo.setText(R.string.ultimoVistoDivision);
                                    break;
                            }
                        } else {
                            tvEjemplo.setText(getString(R.string.ultimoVistoDefault));
                        }
                    }
                });
    }
}