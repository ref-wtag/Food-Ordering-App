package com.example.foodorderingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.foodorderingapp.databinding.FragmentCongratsBottomSheetBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class CongratsBottomSheet extends BottomSheetDialogFragment {
    private FragmentCongratsBottomSheetBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCongratsBottomSheetBinding.inflate(inflater, container, false);
        binding.goHome.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), MainActivity.class);
            startActivity(intent);
        });
        return binding.getRoot();
    }

    public static CongratsBottomSheet newInstance() {
        return new CongratsBottomSheet();
    }
}
