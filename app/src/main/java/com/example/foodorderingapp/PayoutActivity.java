package com.example.foodorderingapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.foodorderingapp.databinding.ActivityPayoutBinding;

public class PayoutActivity extends AppCompatActivity {
    private ActivityPayoutBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflate the binding
        binding = ActivityPayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Set onClickListener for the place order button
        binding.placemyorder.setOnClickListener(view -> {
            CongratsBottomSheet bottomSheetDialogue = new CongratsBottomSheet();
            bottomSheetDialogue.show(getSupportFragmentManager(), "");
        });

        // Set onClickListener for the back button
        binding.imageButtonPayout.setOnClickListener(view -> finish());
    }
}
