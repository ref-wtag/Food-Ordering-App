package com.example.foodorderingapp.adminActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.foodorderingapp.R;
import com.example.foodorderingapp.adminAdapter.AdminApproveOrderAdapter;
import com.example.foodorderingapp.databinding.ActivityAdminApproveOrderBinding;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AdminApproveOrderActivity extends AppCompatActivity {
    private ActivityAdminApproveOrderBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminApproveOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Sample data
        List<String> foodName = Arrays.asList("Burger", "Sandwich", "Momo", "Burger", "Item", "Momo", "Burger", "Item");
        List<String> foodPrice = Arrays.asList("$5", "$10", "$8", "$9", "$8", "$8", "$9", "$8");
        List<Integer> foodImages = Arrays.asList(
                R.drawable.menubreakfast,
                R.drawable.menusalad,
                R.drawable.menufruits,
                R.drawable.menubreakfast,
                R.drawable.menusalad,
                R.drawable.menubreakfast,
                R.drawable.menufruits,
                R.drawable.menusalad
        );

        // Initialize adapter with ArrayList
        AdminApproveOrderAdapter adapter = new AdminApproveOrderAdapter(new ArrayList<>(foodName),
                new ArrayList<>(foodPrice),
                new ArrayList<>(foodImages));
        binding.adminApproveOrderRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.adminApproveOrderRecyclerView.setAdapter(adapter);
    }
}
