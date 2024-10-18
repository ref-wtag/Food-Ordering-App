package com.example.foodorderingapp.adminAdapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodorderingapp.databinding.AdminApproveOrderBinding;

import java.util.List;

public class AdminApproveOrderAdapter extends RecyclerView.Adapter<AdminApproveOrderAdapter.OrderViewHolder> {
    private final List<String> orderItems;
    private final List<String> orderItemsPrice;
    private final List<Integer> orderImages;

    public AdminApproveOrderAdapter(List<String> orderItems, List<String> orderItemsPrice, List<Integer> orderImages) {
        this.orderItems = orderItems;
        this.orderItemsPrice = orderItemsPrice;
        this.orderImages = orderImages;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AdminApproveOrderBinding binding = AdminApproveOrderBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new OrderViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return orderItems.size();
    }

    class OrderViewHolder extends RecyclerView.ViewHolder {
        private final AdminApproveOrderBinding binding;

        public OrderViewHolder(AdminApproveOrderBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(int position) {
            binding.foodNameAdmin.setText(orderItems.get(position));
            binding.priceAdmin.setText(orderItemsPrice.get(position));
            binding.imageView55.setImageResource(orderImages.get(position));

            // Set the button click listener
            binding.approveOrder.setOnClickListener(v -> {
//                if (approveClickListener != null) {
//                    approveClickListener.onApproveClick(position);
//                }
            });
        }
    }

    public interface OnApproveClickListener {
        void onApproveClick(int position);
    }
}
