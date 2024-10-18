package com.example.foodorderingapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.foodorderingapp.databinding.ActivityStartBinding;

public class StartActivity extends AppCompatActivity {
    private ActivityStartBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.nextButton.setOnClickListener(v -> {
            Intent intent;
            if (isUserLoggedIn()) {
                intent = new Intent(StartActivity.this, MainActivity.class);
            } else {
                intent = new Intent(StartActivity.this, LoginActivity.class);
            }
            startActivity(intent);
        });
    }

    private boolean isUserLoggedIn() {
        Context context = getApplicationContext();
        boolean isLoggedIn = context.getSharedPreferences("login", Context.MODE_PRIVATE)
                .getBoolean("isLoggedIn", false);
        long loginTimestamp = context.getSharedPreferences("login", Context.MODE_PRIVATE)
                .getLong("loginTimestamp", 0);

        long currentTime = System.currentTimeMillis();
        long duration = 60 * 60 * 1000; // 1 hour in milliseconds

        return isLoggedIn && (currentTime - loginTimestamp < duration);
    }
}
