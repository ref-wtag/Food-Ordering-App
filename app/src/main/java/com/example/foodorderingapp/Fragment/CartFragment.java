package com.example.foodorderingapp.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.foodorderingapp.PayoutActivity;
import com.example.foodorderingapp.R;
import com.example.foodorderingapp.adapter.CartAdapter;
import com.example.foodorderingapp.databinding.FragmentCartBinding;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CartFragment extends Fragment {

    private FragmentCartBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCartBinding.inflate(inflater, container, false);

        // Sample data
        List<String> cartFoodName = Arrays.asList("Burger", "Sandwich", "Momo", "Burger", "Item");
        List<String> foodPrice = Arrays.asList("$5", "$10", "$8", "$9", "$8");
        List<Integer> foodImages = Arrays.asList(
                R.drawable.menubreakfast,
                R.drawable.menusalad,
                R.drawable.menufruits,
                R.drawable.menubreakfast,
                R.drawable.menusalad
        );

        // Set up adapter
        CartAdapter adapter = new CartAdapter(new ArrayList<>(cartFoodName), new ArrayList<>(foodPrice), new ArrayList<>(foodImages));
        binding.cartRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.cartRecyclerView.setAdapter(adapter);

        // Proceed button onClick
        binding.proceedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireContext(), PayoutActivity.class);
                startActivity(intent);
            }
        });

        return binding.getRoot();
    }
}