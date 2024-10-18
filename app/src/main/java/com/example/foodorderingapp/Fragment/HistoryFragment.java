package com.example.foodorderingapp.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.foodorderingapp.R;
import com.example.foodorderingapp.adapter.BuyAgainAdapter;
import com.example.foodorderingapp.databinding.FragmentHistoryBinding;
import java.util.ArrayList;

public class HistoryFragment extends Fragment {

    private FragmentHistoryBinding binding;
    private BuyAgainAdapter buyAgainAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHistoryBinding.inflate(inflater, container, false);
        setUpRecyclerView();
        return binding.getRoot();
    }

    private void setUpRecyclerView() {
        ArrayList<String> buyAgainFoodName = new ArrayList<>();
        buyAgainFoodName.add("Food 1");
        buyAgainFoodName.add("Food 2");
        buyAgainFoodName.add("Food 3");
        buyAgainFoodName.add("Food 4");
        buyAgainFoodName.add("Food 5");

        ArrayList<String> buyAgainFoodPrice = new ArrayList<>();
        buyAgainFoodPrice.add("$5");
        buyAgainFoodPrice.add("$6");
        buyAgainFoodPrice.add("$7");
        buyAgainFoodPrice.add("$8");
        buyAgainFoodPrice.add("$9");

        ArrayList<Integer> buyAgainFoodImage = new ArrayList<>();
        buyAgainFoodImage.add(R.drawable.menubreakfast);
        buyAgainFoodImage.add(R.drawable.menusalad);
        buyAgainFoodImage.add(R.drawable.menubreakfast);
        buyAgainFoodImage.add(R.drawable.menusalad);
        buyAgainFoodImage.add(R.drawable.menufruits);

        buyAgainAdapter = new BuyAgainAdapter(buyAgainFoodName, buyAgainFoodPrice, buyAgainFoodImage);
        binding.buyAgainRecyclerView.setAdapter(buyAgainAdapter);
        binding.buyAgainRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
    }
}
