package com.example.foodorderingapp.activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.foodorderingapp.R;
import com.example.foodorderingapp.bottomSheetFragment.NotificationBottomFragment;
import com.example.foodorderingapp.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        NavController navController = Navigation.findNavController(this, R.id.fragmentContainerView);
        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigationView);
        NavigationUI.setupWithNavController(bottomNav, navController);

        binding.notificationIcon.setOnClickListener(v -> {
            NotificationBottomFragment bottomSheetDialogue = new NotificationBottomFragment();
            bottomSheetDialogue.show(getSupportFragmentManager(), "tag");
        });
    }
}
