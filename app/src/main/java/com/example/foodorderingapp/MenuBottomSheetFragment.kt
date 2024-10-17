package com.example.foodorderingapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodorderingapp.adapter.MenuAdapter
import com.example.foodorderingapp.databinding.FragmentMenuBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class MenuBottomSheetFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentMenuBottomSheetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMenuBottomSheetBinding.inflate(inflater, container, false)
        binding.buttonBack.setOnClickListener{
            dismiss()
        }
        val menuFoodName = listOf("Burger", "Sandwitch", "momo", "Burger", "Item", "Burger", "Sandwitch", "momo", "Burger", "Item")
        var menuFoodPrice = listOf("$5", "$10", "$8", "$9", "$8", "$5", "$10", "$8", "$9", "$8")
        var menuFoodImages = listOf(
            R.drawable.menubreakfast,
            R.drawable.menusalad,
            R.drawable.menufruits,
            R.drawable.menubreakfast,
            R.drawable.menusalad,
            R.drawable.menubreakfast,
            R.drawable.menusalad,
            R.drawable.menufruits,
            R.drawable.menubreakfast,
            R.drawable.menusalad
        )
        val adapter = MenuAdapter(ArrayList(menuFoodName), ArrayList(menuFoodPrice), ArrayList(menuFoodImages), requireContext())
        binding.menuRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.menuRecyclerView.adapter = adapter
        return binding.root
    }
}