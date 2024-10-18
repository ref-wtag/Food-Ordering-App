package com.example.foodorderingapp.admin;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodorderingapp.R;

public class AdminHomeActivity extends AppCompatActivity {

    private Button addFoodButton;
    private Button approveOrderButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        addFoodButton = findViewById(R.id.addNewItem);
        approveOrderButton = findViewById(R.id.approveOrders);

        addFoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        approveOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
    }
}
