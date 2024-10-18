package com.example.foodorderingapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.example.foodorderingapp.activity.LoginActivity;
import com.example.foodorderingapp.activity.MainActivity;
import com.example.foodorderingapp.admin.AdminHomeActivity;

public class ChoosePanelActivity extends AppCompatActivity {

    private Button buttonAdmin, buttonUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_panel);

        buttonAdmin = findViewById(R.id.goToAdminPanel);
        buttonUser = findViewById(R.id.goToUserPanel);

        buttonAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChoosePanelActivity.this, AdminHomeActivity.class);
                startActivity(intent);
            }
        });

        buttonUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                if (isUserLoggedIn()) {
                    intent = new Intent(ChoosePanelActivity.this, MainActivity.class);
                } else {
                    intent = new Intent(ChoosePanelActivity.this, LoginActivity.class);
                }
                startActivity(intent);
            }
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
