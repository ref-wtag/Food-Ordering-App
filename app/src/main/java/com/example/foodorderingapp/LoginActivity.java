package com.example.foodorderingapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.foodorderingapp.databinding.ActivityLoginBinding;
import com.example.foodorderingapp.model.User;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.examples.realmmigrationexample.model.Migration;

public class LoginActivity extends AppCompatActivity {
    private Realm realm;
    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Realm.init(this);
        RealmConfiguration config1 = new RealmConfiguration.Builder()
                .name("default1.realm")
                .schemaVersion(3) // Set the schema version
                .migration(new Migration()) // Set the migration class
                .build();

        Realm.setDefaultConfiguration(config1);
        realm = Realm.getDefaultInstance();

        binding.loginButton.setOnClickListener(v -> {
            String userEmail = binding.emailTextLogin.getText().toString();
            String userPassword = binding.passwordTextLogin.getText().toString();
            loginUser(userEmail, userPassword);
        });

        binding.dontHaveButton.setOnClickListener(v -> {
            binding.emailTextLogin.setText("");
            binding.passwordTextLogin.setText("");
            Intent intent = new Intent(this, SignUpActivity.class);
            startActivity(intent);
        });
    }

    private void loginUser(String userEmail, String userPassword) {
        realm.executeTransactionAsync(realm -> {
            User user = realm.where(User.class)
                    .equalTo("userEmail", userEmail)
                    .equalTo("userPassword", userPassword) // Check username and password
                    .findFirst();

            if (user != null) {
                if ("admin".equals(user.getUserType())) {
                    // startActivity(new Intent(this, AdminDashboardActivity.class));
                } else {
                    saveLoginInfo(userEmail);
                    binding.emailTextLogin.setText("");
                    binding.passwordTextLogin.setText("");
                    startActivity(new Intent(this, MainActivity.class));
                }
            } else {
                // If username or password is incorrect
                new Handler(Looper.getMainLooper()).post(() ->
                        Toast.makeText(LoginActivity.this, "Invalid username or password!", Toast.LENGTH_SHORT).show());
            }
        });
    }

    private void saveLoginInfo(String email) {
        Context context = getApplicationContext();
        context.getSharedPreferences("login", Context.MODE_PRIVATE)
                .edit()
                .putString("userEmail", email)
                .putLong("loginTimestamp", System.currentTimeMillis())
                .putBoolean("isLoggedIn", true)
                .apply();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
