package com.example.foodorderingapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.foodorderingapp.DetailsActivity;
import com.example.foodorderingapp.databinding.PopularItemBinding;
import java.util.List;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.PopularViewHolder> {

    private final List<String> items;
    private final List<String> price;
    private final List<Integer> image;
    private final Context requiredContext;

    public PopularAdapter(List<String> items, List<String> price, List<Integer> image, Context requiredContext) {
        this.items = items;
        this.price = price;
        this.image = image;
        this.requiredContext = requiredContext;
    }

    @NonNull
    @Override
    public PopularViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        PopularItemBinding binding = PopularItemBinding.inflate(inflater, parent, false);
        return new PopularViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularViewHolder holder, int position) {
        String item = items.get(position);
        int images = image.get(position);
        String priceValue = price.get(position);

        holder.bind(item, priceValue, images);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requiredContext, DetailsActivity.class);
                intent.putExtra("MenuItemName", item);
                intent.putExtra("MenuItemImage", images);
                requiredContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class PopularViewHolder extends RecyclerView.ViewHolder {

        private final PopularItemBinding binding;

        public PopularViewHolder(PopularItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(String item, String price, int images) {
            binding.foodNamePopular.setText(item);
            binding.pricePopular.setText(price);
            binding.imageView5.setImageResource(images);
        }
    }
}
