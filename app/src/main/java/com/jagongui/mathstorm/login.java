package com.jagongui.mathstorm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity {

    EditText username;
    EditText password;
    Button LoginButton;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        username = findViewById(R.id.Username);
        password = findViewById(R.id.Password);
        LoginButton = findViewById(R.id.buttonLogin);

        LoginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String user = username.getText().toString().trim();
                String pass = password.getText().toString().trim();

                if(user.isEmpty() || pass.isEmpty()){
                    Toast.makeText(login.this, "Introduzca sus credenciales", Toast.LENGTH_SHORT).show();
                }else{

                    loginUser(user,pass);
                }
            }
        });

    }
    private void loginUser(String username,String password){
        mAuth.signInWithEmailAndPassword(username,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    finish();
                    startActivity(new Intent(login.this, MainActivity.class));
                    Toast.makeText(login.this, getString(R.string.LoginSuccessful) , Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(login.this, getString(R.string.LoginUnsusccessful), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if(user != null){
            startActivity(new Intent(login.this, MainActivity.class));
            finish();
        }
    }

    public void openSignup(View v){
        Intent intent = new Intent(login.this, signup.class);
        startActivity(intent);
    }

}