package com.example.foodorderingapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.foodorderingapp.DetailsActivity;
import com.example.foodorderingapp.databinding.MenuItemBinding;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {
    private final List<String> menuItems;
    private final List<String> menuItemPrice;
    private final List<Integer> menuImages;
    private final Context requiredContext;

    public MenuAdapter(List<String> menuItems, List<String> menuItemPrice, List<Integer> menuImages, Context requiredContext) {
        this.menuItems = menuItems;
        this.menuItemPrice = menuItemPrice;
        this.menuImages = menuImages;
        this.requiredContext = requiredContext;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MenuItemBinding binding = MenuItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MenuViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return menuItems.size();
    }

    class MenuViewHolder extends RecyclerView.ViewHolder {
        private final MenuItemBinding binding;

        public MenuViewHolder(MenuItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.getRoot().setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    // Set onClick to open details
                    Intent intent = new Intent(requiredContext, DetailsActivity.class);
                    intent.putExtra("MenuItemName", menuItems.get(position));
                    intent.putExtra("MenuItemImage", menuImages.get(position));
                    requiredContext.startActivity(intent);
                }
            });
        }

        public void bind(int position) {
            binding.menuFoodName.setText(menuItems.get(position));
            binding.menuPrice.setText(menuItemPrice.get(position));
            binding.menuImage.setImageResource(menuImages.get(position));
        }
    }
}
