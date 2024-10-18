package com.example.foodorderingapp.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.foodorderingapp.R;
import com.example.foodorderingapp.adapter.MenuAdapter;
import com.example.foodorderingapp.databinding.FragmentSearchBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchFragment extends Fragment {
    private FragmentSearchBinding binding;
    private MenuAdapter adapter;

    private final List<String> originalFoodMenuName = Arrays.asList(
            "Burger", "Sandwitch", "momo", "Burger", "Item",
            "Burger", "Sandwitch", "momo", "Burger", "Item"
    );

    private final List<String> originalFoodPrice = Arrays.asList(
            "$5", "$10", "$8", "$9", "$8",
            "$5", "$10", "$8", "$9", "$8"
    );

    private final List<Integer> originalItemImage = Arrays.asList(
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

    private final List<String> filterMenuFoodName = new ArrayList<>();
    private final List<String> filterMenuFoodPrice = new ArrayList<>();
    private final List<Integer> filterMenuFoodImage = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSearchBinding.inflate(inflater, container, false);
        adapter = new MenuAdapter(filterMenuFoodName, filterMenuFoodPrice, filterMenuFoodImage, requireContext());
        binding.menuRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.menuRecyclerView.setAdapter(adapter);

        // Setup for search view
        setUpSearchView();

        // Show all menu items
        showAllMenuItems();

        return binding.getRoot();
    }

    private void showAllMenuItems() {
        filterMenuFoodName.clear();
        filterMenuFoodPrice.clear();
        filterMenuFoodImage.clear();

        filterMenuFoodName.addAll(originalFoodMenuName);
        filterMenuFoodPrice.addAll(originalFoodPrice);
        filterMenuFoodImage.addAll(originalItemImage);
    }

    private void setUpSearchView() {
        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filterMenuItems(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterMenuItems(newText);
                return true;
            }
        });
    }

    private void filterMenuItems(String query) {
        filterMenuFoodName.clear();
        filterMenuFoodPrice.clear();
        filterMenuFoodImage.clear();

        for (int index = 0; index < originalFoodMenuName.size(); index++) {
            String foodName = originalFoodMenuName.get(index);
            if (foodName.toLowerCase().contains(query != null ? query.toLowerCase() : "")) {
                filterMenuFoodName.add(foodName);
                filterMenuFoodPrice.add(originalFoodPrice.get(index));
                filterMenuFoodImage.add(originalItemImage.get(index));
            }
        }

        adapter.notifyDataSetChanged();
    }
}
