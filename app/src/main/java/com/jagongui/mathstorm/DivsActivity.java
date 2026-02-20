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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Random;

public class DivsActivity extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String userId = FirebaseAuth.getInstance().getUid();
    DocumentReference userRef = db.collection("user").document(userId);
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

        if (userAnswer.isEmpty() || userAnswerResto.isEmpty()) {
            tilAnswer.setError("Introduce una respuesta");
            return;
        }

        if (Integer.parseInt(userAnswer) == correctAnswerC  && Integer.parseInt(userAnswerResto) == correctAnswerR) {
            tilAnswer.setError(null);
            tilAnswerR.setError(null);
            Toast.makeText(this, "✅ Correcto", Toast.LENGTH_SHORT).show();
            updateDailyStreak();
            userRef.update("exercisesCompleted", FieldValue.increment(1));
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
    private String getTodayDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return sdf.format(new Date());
    }

    private long daysBetween(String lastDate, String today) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        try {
            Date last = sdf.parse(lastDate);
            Date now = sdf.parse(today);
            Calendar cLast = Calendar.getInstance();
            Calendar cNow = Calendar.getInstance();
            cLast.setTime(last);
            cNow.setTime(now);
            // Normalizamos a medianoche
            cLast.set(Calendar.HOUR_OF_DAY, 0);
            cLast.set(Calendar.MINUTE, 0);
            cLast.set(Calendar.SECOND, 0);
            cLast.set(Calendar.MILLISECOND, 0);

            cNow.set(Calendar.HOUR_OF_DAY, 0);
            cNow.set(Calendar.MINUTE, 0);
            cNow.set(Calendar.SECOND, 0);
            cNow.set(Calendar.MILLISECOND, 0);

            long diffMillis = cNow.getTimeInMillis() - cLast.getTimeInMillis();
            return diffMillis / (1000 * 60 * 60 * 24);
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private void updateDailyStreak() {
        String today = getTodayDate();
        userRef.get().addOnSuccessListener(document -> {
            if (document.exists()) {
                String lastDate = document.getString("lastLoginDate");
                Long streak = document.getLong("streak");
                Long maxStreak = document.getLong("maxStreak");
                if (lastDate == null || streak == null) {
                    userRef.update(
                            "streak", 1,
                            "lastLoginDate", today,
                            "maxStreak", 1
                    );
                    return;
                }
                long daysDiff = daysBetween(lastDate, today);
                if (daysDiff == 1) {
                    streak++;
                } else if (daysDiff > 1) {
                    streak = 1L;
                } else {
                    // mismo día → no se suma
                    return;
                }
                if (maxStreak == null || streak > maxStreak) {
                    maxStreak = streak;
                }
                userRef.update(
                        "streak", streak,
                        "lastLoginDate", today,
                        "maxStreak", maxStreak
                );
            } else {
                // Primera vez
                HashMap<String, Object> data = new HashMap<>();
                data.put("streak", 1);
                data.put("lastLoginDate", today);
                data.put("maxStreak", 1);
                userRef.set(data);
            }
        });
    }
}
