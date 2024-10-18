package com.example.foodorderingapp.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.foodorderingapp.databinding.CartItemBinding;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private final List<String> cartItems;
    private final List<String> cartItemsPrice;
    private final List<Integer> cartImages;
    private final int[] itemQuantities;

    public CartAdapter(List<String> cartItems, List<String> cartItemsPrice, List<Integer> cartImages) {
        this.cartItems = cartItems;
        this.cartItemsPrice = cartItemsPrice;
        this.cartImages = cartImages;
        this.itemQuantities = new int[cartImages.size()];
        for (int i = 0; i < itemQuantities.length; i++) {
            itemQuantities[i] = 1; // Initialize quantities to 1
        }
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CartItemBinding binding = CartItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CartViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    class CartViewHolder extends RecyclerView.ViewHolder {
        private final CartItemBinding binding;

        public CartViewHolder(CartItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(int position) {
            binding.cartFoodName.setText(cartItems.get(position));
            binding.cartItemPrice.setText(cartItemsPrice.get(position));
            binding.cartImage.setImageResource(cartImages.get(position));
            binding.quantity.setText(String.valueOf(itemQuantities[position]));

            binding.minusButton.setOnClickListener(v -> decreaseQuantity(position));
            binding.plusButton.setOnClickListener(v -> increaseQuantity(position));
            binding.deleteButton.setOnClickListener(v -> {
                int itemPosition = getAdapterPosition();
                if (itemPosition != RecyclerView.NO_POSITION) {
                    deleteItem(itemPosition);
                }
            });
        }

        private void decreaseQuantity(int position) {
            if (itemQuantities[position] > 1) {
                itemQuantities[position]--;
                binding.quantity.setText(String.valueOf(itemQuantities[position]));
            }
        }

        private void increaseQuantity(int position) {
            if (itemQuantities[position] < 10) {
                itemQuantities[position]++;
                binding.quantity.setText(String.valueOf(itemQuantities[position]));
            }
        }

        private void deleteItem(int position) {
            cartItems.remove(position);
            cartItemsPrice.remove(position);
            cartImages.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, cartItems.size());
        }
    }
}
