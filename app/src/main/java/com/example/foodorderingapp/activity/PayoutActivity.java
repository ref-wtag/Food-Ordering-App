package com.example.foodorderingapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.foodorderingapp.bottomSheetFragment.CongratsBottomSheet;
import com.example.foodorderingapp.databinding.ActivityPayoutBinding;

public class PayoutActivity extends AppCompatActivity {
    private ActivityPayoutBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.placemyorder.setOnClickListener(v -> {
            CongratsBottomSheet bottomSheetDialogue = new CongratsBottomSheet();
            bottomSheetDialogue.show(getSupportFragmentManager(), "");
        });

        binding.imageButtonPayout.setOnClickListener(v -> finish());
    }
}
