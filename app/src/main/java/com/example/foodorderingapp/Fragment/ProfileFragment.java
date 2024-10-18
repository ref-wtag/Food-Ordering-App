package com.example.foodorderingapp.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodorderingapp.activity.LoginActivity;
import com.example.foodorderingapp.databinding.FragmentProfileBinding;

public class ProfileFragment extends Fragment {
    private FragmentProfileBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);

        binding.logOut.setOnClickListener(v -> {
            logoutUser();
            Intent intent = new Intent(requireContext(), LoginActivity.class);
            startActivity(intent);
        });

        return binding.getRoot();
    }

    private void logoutUser() {
        Context context = requireContext();
        context.getSharedPreferences("login", Context.MODE_PRIVATE).edit()
                .clear()
                .apply();
    }
}
