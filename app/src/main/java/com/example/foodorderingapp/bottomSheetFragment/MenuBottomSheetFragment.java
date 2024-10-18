package com.example.foodorderingapp.bottomSheetFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.foodorderingapp.R;
import com.example.foodorderingapp.adapter.MenuAdapter;
import com.example.foodorderingapp.databinding.FragmentMenuBottomSheetBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MenuBottomSheetFragment extends BottomSheetDialogFragment {
    private FragmentMenuBottomSheetBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMenuBottomSheetBinding.inflate(inflater, container, false);

        binding.buttonBack.setOnClickListener(v -> dismiss());

        List<String> menuFoodName = Arrays.asList(
                "Burger", "Sandwich", "Momo", "Burger", "Item",
                "Burger", "Sandwich", "Momo", "Burger", "Item"
        );
        List<String> menuFoodPrice = Arrays.asList(
                "$5", "$10", "$8", "$9", "$8",
                "$5", "$10", "$8", "$9", "$8"
        );
        List<Integer> menuFoodImages = Arrays.asList(
                R.drawable.menubreakfast, R.drawable.menusalad, R.drawable.menufruits,
                R.drawable.menubreakfast, R.drawable.menusalad,
                R.drawable.menubreakfast, R.drawable.menusalad, R.drawable.menufruits,
                R.drawable.menubreakfast, R.drawable.menusalad
        );

        MenuAdapter adapter = new MenuAdapter(new ArrayList<>(menuFoodName), new ArrayList<>(menuFoodPrice), new ArrayList<>(menuFoodImages), requireContext());
        binding.menuRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.menuRecyclerView.setAdapter(adapter);

        return binding.getRoot();
    }
}
