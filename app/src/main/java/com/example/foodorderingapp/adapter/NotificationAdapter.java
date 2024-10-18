package com.example.foodorderingapp.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.foodorderingapp.databinding.NotificationItemBinding;
import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder> {

    private final ArrayList<String> notification;
    private final ArrayList<Integer> notificationImages;

    public NotificationAdapter(ArrayList<String> notification, ArrayList<Integer> notificationImages) {
        this.notification = notification;
        this.notificationImages = notificationImages;
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        NotificationItemBinding binding = NotificationItemBinding.inflate(inflater, parent, false);
        return new NotificationViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return notification.size();
    }

    public class NotificationViewHolder extends RecyclerView.ViewHolder {

        private final NotificationItemBinding binding;

        public NotificationViewHolder(NotificationItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(int position) {
            binding.notificationTextView.setText(notification.get(position));
            binding.notificationImage.setImageResource(notificationImages.get(position));
        }
    }
}
