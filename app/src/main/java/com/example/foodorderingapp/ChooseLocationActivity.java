package com.example.foodorderingapp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.foodorderingapp.databinding.ActivityChooseLocationBinding;

public class ChooseLocationActivity extends AppCompatActivity {
    private ActivityChooseLocationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChooseLocationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // List of locations
        String[] locationList = {"Dhaka", "Chittagong", "Khulna", "Rangpur", "Rajshahi"};

        // Set up the ArrayAdapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, locationList);
        AutoCompleteTextView autoCompleteTextView = binding.listOfLocation;
        autoCompleteTextView.setAdapter(adapter);

        // Optional: Set a listener to handle item selection
        autoCompleteTextView.setOnItemClickListener((parent, view, position, id) -> {
            String selectedLocation = (String) parent.getItemAtPosition(position);
            Toast.makeText(this, "Selected: " + selectedLocation, Toast.LENGTH_SHORT).show();
        });
    }
}
