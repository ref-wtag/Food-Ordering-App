package com.example.foodorderingapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import com.example.foodorderingapp.databinding.ActivityChooseLocationBinding;

public class ChooseLocationActivity extends AppCompatActivity {
    private ActivityChooseLocationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChooseLocationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String[] locationList = {"Dhaka", "Chittagong", "Khulna", "Rangpur", "Rajshahi"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, locationList);
        binding.listOfLocation.setAdapter(adapter);
    }
}
