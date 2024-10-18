package com.example.foodorderingapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.foodorderingapp.databinding.ActivitySignUpBinding;
import com.example.foodorderingapp.model.User;
import io.realm.Realm;
import java.util.UUID;

public class SignUpActivity extends AppCompatActivity {
    private Realm realm;
    private ActivitySignUpBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Realm.init(this);
        realm = Realm.getDefaultInstance();

        binding.alreadyHaveButton.setOnClickListener(v -> {
            Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        binding.createAccount.setOnClickListener(v -> {
            String name = binding.nameRegistration.getText().toString();
            String email = binding.emailRegistration.getText().toString();
            String password = binding.passwordRegistration.getText().toString();

            registerUser(name, email, password);
        });
    }

    private void registerUser(String username, String email, String password) {
        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || !isValidEmail(email)) {
            Toast.makeText(this, "Invalid username, email, or password", Toast.LENGTH_SHORT).show();
            return;
        }

        User existingUser = realm.where(User.class).equalTo("userEmail", email).findFirst();

        if (existingUser != null) {
            Toast.makeText(this, "Username already exists!", Toast.LENGTH_LONG).show();
        } else {
            realm.executeTransactionAsync(realm -> {
                User newUser = realm.createObject(User.class, UUID.randomUUID().toString());
                newUser.setUserName(username);
                newUser.setUserEmail(email);
                newUser.setUserPassword(password);
                newUser.setUserType("customer");

                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(() -> {
                    Toast.makeText(SignUpActivity.this, "User registered successfully!", Toast.LENGTH_LONG).show();
                    binding.nameRegistration.setText("");
                    binding.emailRegistration.setText("");
                    binding.passwordRegistration.setText("");
                });

                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                finish();
            });
        }
    }

    private boolean isValidEmail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
