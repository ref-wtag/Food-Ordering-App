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
    private OnClickListener itemClickListener;

    public MenuAdapter(List<String> menuItems, List<String> menuItemPrice, List<Integer> menuImages, Context requiredContext) {
        this.menuItems = menuItems;
        this.menuItemPrice = menuItemPrice;
        this.menuImages = menuImages;
        this.requiredContext = requiredContext;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        MenuItemBinding binding = MenuItemBinding.inflate(inflater, parent, false);
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

    public class MenuViewHolder extends RecyclerView.ViewHolder {

        private final MenuItemBinding binding;

        public MenuViewHolder(MenuItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && itemClickListener != null) {
                        itemClickListener.onItemClick(position);
                    }

                    // Set on click to open details
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

    public interface OnClickListener {
        void onItemClick(int position);
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.itemClickListener = onClickListener;
    }
}
