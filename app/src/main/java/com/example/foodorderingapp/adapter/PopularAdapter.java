package com.example.foodorderingapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.foodorderingapp.DetailsActivity;
import com.example.foodorderingapp.databinding.PopularItemBinding;

import java.util.List;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.PopularViewHolder> {
    private final List<String> items;
    private final List<String> prices;
    private final List<Integer> images;
    private final Context requiredContext;

    public PopularAdapter(List<String> items, List<String> prices, List<Integer> images, Context requiredContext) {
        this.items = items;
        this.prices = prices;
        this.images = images;
        this.requiredContext = requiredContext;
    }

    @NonNull
    @Override
    public PopularViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PopularItemBinding binding = PopularItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new PopularViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularViewHolder holder, int position) {
        String item = items.get(position);
        String price = prices.get(position);
        int image = images.get(position);
        holder.bind(item, price, image);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(requiredContext, DetailsActivity.class);
            intent.putExtra("MenuItemName", item);
            intent.putExtra("MenuItemImage", image);
            requiredContext.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class PopularViewHolder extends RecyclerView.ViewHolder {
        private final PopularItemBinding binding;

        public PopularViewHolder(PopularItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(String item, String price, int image) {
            binding.foodNamePopular.setText(item);
            binding.pricePopular.setText(price);
            binding.imageView5.setImageResource(image);
        }
    }
}
