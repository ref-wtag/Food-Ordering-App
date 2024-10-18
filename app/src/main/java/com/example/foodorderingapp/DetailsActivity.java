package com.example.foodorderingapp;

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

        // Retrieve data from the intent
        String foodName = getIntent().getStringExtra("MenuItemName");
        int foodImage = getIntent().getIntExtra("MenuItemImage", 0);

        // Set the food name and image
        binding.detailFoodName.setText(foodName);
        binding.detailFoodImage.setImageResource(foodImage);

        // Set click listener to finish the activity
        binding.imageButtonDetail.setOnClickListener(v -> finish());
    }
}
