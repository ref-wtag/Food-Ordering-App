package com.example.foodorderingapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.foodorderingapp.databinding.ActivityDetailsBinding;

public class DetailsActivity extends AppCompatActivity {
    private ActivityDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String foodName = getIntent().getStringExtra("MenuItemName");
        int foodImage = getIntent().getIntExtra("MenuItemImage", 0);

        binding.detaillFoodName.setText(foodName);
        binding.detailFoodImage.setImageResource(foodImage);

        binding.imageButtonDetail.setOnClickListener(v -> finish());
    }
}
