package com.example.foodorderingapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.foodorderingapp.adapter.NotificationAdapter;
import com.example.foodorderingapp.databinding.FragmentNotificationBottomBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Notification_bottom_Fragment extends BottomSheetDialogFragment {
    private FragmentNotificationBottomBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout using view binding
        binding = FragmentNotificationBottomBinding.inflate(inflater, container, false);

        // Sample notification data
        List<String> notifications = Arrays.asList(
                "Your order has been cancelled",
                "Order has been taken by the driver",
                "Congrats! Your order has been placed"
        );

        List<Integer> notificationImages = Arrays.asList(
                R.drawable.sademoji,
                R.drawable.icon__3_,
                R.drawable.illustration
        );

        // Create and set up the adapter
        NotificationAdapter adapter = new NotificationAdapter(
                new ArrayList<>(notifications),
                new ArrayList<>(notificationImages)
        );

        // Set the layout manager and adapter for the RecyclerView
        binding.notificationRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.notificationRecyclerView.setAdapter(adapter);

        // Return the root view of the binding
        return binding.getRoot();
    }

    public static class Companion {
        // Optional: Add any static methods or fields if necessary
    }
}
