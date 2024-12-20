package com.example.foodorderingapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodorderingapp.adapter.NotificationAdapter
import com.example.foodorderingapp.databinding.FragmentNotificationBottomBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class Notification_bottom_Fragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentNotificationBottomBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotificationBottomBinding.inflate(layoutInflater, container, false)
        val notification = listOf("Your order Has been cancelled", "Order has been taken by the driver", "Congrats Your Order Has Placed")
        val notificationImages = listOf(
            R.drawable.sademoji,
            R.drawable.icon__3_,
            R.drawable.illustration
        )
        val adapter = NotificationAdapter(ArrayList(notification), ArrayList(notificationImages))
        binding.notificationRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.notificationRecyclerView.adapter = adapter
        return binding.root
    }

    companion object {

    }
}