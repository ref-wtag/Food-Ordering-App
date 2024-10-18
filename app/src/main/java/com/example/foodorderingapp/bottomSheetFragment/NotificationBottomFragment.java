package com.example.foodorderingapp.bottomSheetFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.foodorderingapp.R;
import com.example.foodorderingapp.adapter.NotificationAdapter;
import com.example.foodorderingapp.databinding.FragmentNotificationBottomBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NotificationBottomFragment extends BottomSheetDialogFragment {
    private FragmentNotificationBottomBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentNotificationBottomBinding.inflate(inflater, container, false);

        List<String> notification = Arrays.asList(
                "Your order has been cancelled",
                "Order has been taken by the driver",
                "Congrats! Your order has been placed"
        );
        List<Integer> notificationImages = Arrays.asList(
                R.drawable.sademoji,
                R.drawable.icon__3_,
                R.drawable.illustration
        );

        NotificationAdapter adapter = new NotificationAdapter(new ArrayList<>(notification), new ArrayList<>(notificationImages));
        binding.notificationRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.notificationRecyclerView.setAdapter(adapter);

        return binding.getRoot();
    }
}
