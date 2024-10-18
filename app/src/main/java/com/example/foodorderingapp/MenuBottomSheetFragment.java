package com.example.foodorderingapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.foodorderingapp.adapter.MenuAdapter;
import com.example.foodorderingapp.databinding.FragmentMenuBottomSheetBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MenuBottomSheetFragment extends BottomSheetDialogFragment {
    private FragmentMenuBottomSheetBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout using view binding
        binding = FragmentMenuBottomSheetBinding.inflate(inflater, container, false);

        // Set up back button to dismiss the bottom sheet
        binding.buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        // Sample data for the menu
        List<String> menuFoodName = Arrays.asList("Burger", "Sandwich", "Momo", "Burger", "Item",
                "Burger", "Sandwich", "Momo", "Burger", "Item");
        List<String> menuFoodPrice = Arrays.asList("$5", "$10", "$8", "$9", "$8",
                "$5", "$10", "$8", "$9", "$8");
        List<Integer> menuFoodImages = Arrays.asList(
                R.drawable.menubreakfast,
                R.drawable.menusalad,
                R.drawable.menufruits,
                R.drawable.menubreakfast,
                R.drawable.menusalad,
                R.drawable.menubreakfast,
                R.drawable.menusalad,
                R.drawable.menufruits,
                R.drawable.menubreakfast,
                R.drawable.menusalad
        );

        // Create and set up the adapter
        MenuAdapter adapter = new MenuAdapter(
                new ArrayList<>(menuFoodName),
                new ArrayList<>(menuFoodPrice),
                new ArrayList<>(menuFoodImages),
                requireContext()
        );

        // Set the layout manager and adapter for the RecyclerView
        binding.menuRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.menuRecyclerView.setAdapter(adapter);

        // Return the root view of the binding
        return binding.getRoot();
    }
}
