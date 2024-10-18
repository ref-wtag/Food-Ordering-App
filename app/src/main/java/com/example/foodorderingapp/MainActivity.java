package com.example.foodorderingapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import com.example.foodorderingapp.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Set up the navigation controller
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
        NavController navController = navHostFragment.getNavController();

        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigationView);
        NavigationUI.setupWithNavController(bottomNav, navController);

        // Set onClickListener for the notification icon
        binding.notificationIcon.setOnClickListener(v -> {
            Notification_bottom_Fragment bottomSheetDialog = new Notification_bottom_Fragment();
            bottomSheetDialog.show(getSupportFragmentManager(), "tag");
        });
    }
}

//package com.example.foodorderingapp;
//
//        import androidx.appcompat.app.AppCompatActivity;
//        import android.os.Bundle;
//        import androidx.navigation.NavController;
//        import androidx.navigation.findNavController;
//        import androidx.navigation.ui.NavigationUI;
//        import com.example.foodorderingapp.databinding.ActivityMainBinding;
//        import com.google.android.material.bottomnavigation.BottomNavigationView;

//public class MainActivity extends AppCompatActivity {
//    private ActivityMainBinding binding;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        binding = ActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//
//        // Set up the navigation controller
//        NavController navController = findNavController(R.id.fragmentContainerView);
//        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigationView);
//        NavigationUI.setupWithNavController(bottomNav, navController);
//
//        // Set onClickListener for the notification icon
//        binding.notificationIcon.setOnClickListener(v -> {
//            Notification_bottom_Fragment bottomSheetDialog = new Notification_bottom_Fragment();
//            bottomSheetDialog.show(getSupportFragmentManager(), "tag");
//        });
//    }
//}

