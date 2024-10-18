package com.example.foodorderingapp.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.interfaces.ItemClickListener;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.foodorderingapp.bottomSheetFragment.MenuBottomSheetFragment;
import com.example.foodorderingapp.R;
import com.example.foodorderingapp.adapter.PopularAdapter;
import com.example.foodorderingapp.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        binding.viewAllMenu.setOnClickListener(v -> {
            MenuBottomSheetFragment bottomSheetDialogue = new MenuBottomSheetFragment();
            bottomSheetDialogue.show(getParentFragmentManager(), "tag");
        });
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Setting up the image slider
        List<SlideModel> imageList = new ArrayList<>();
        imageList.add(new SlideModel(R.drawable.banner1, ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.banner2, ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.banner3, ScaleTypes.FIT));

        binding.imageSlider.setImageList(imageList);
        binding.imageSlider.setImageList(imageList, ScaleTypes.FIT);

        binding.imageSlider.setItemClickListener(new ItemClickListener() {
            @Override
            public void doubleClick(int position) {
                // Not yet implemented
            }

            @Override
            public void onItemSelected(int position) {
                SlideModel itemPosition = imageList.get(position);
                String itemMessage = "Selected Image " + (position + 1);
                Toast.makeText(requireContext(), itemMessage, Toast.LENGTH_SHORT).show();
            }
        });

        // Setting up the RecyclerView for popular items
        List<String> foodName = new ArrayList<>();
        foodName.add("Burger");
        foodName.add("Sandwich");
        foodName.add("Momo");
        foodName.add("Item");

        List<String> price = new ArrayList<>();
        price.add("$5");
        price.add("$7");
        price.add("$8");
        price.add("$10");

        List<Integer> foodImages = new ArrayList<>();
        foodImages.add(R.drawable.menubreakfast);
        foodImages.add(R.drawable.menufruits);
        foodImages.add(R.drawable.menusalad);
        foodImages.add(R.drawable.menusalad);

        PopularAdapter adapter = new PopularAdapter(foodName, price, foodImages, requireContext());
        binding.popularRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.popularRecyclerView.setAdapter(adapter);
    }
}
