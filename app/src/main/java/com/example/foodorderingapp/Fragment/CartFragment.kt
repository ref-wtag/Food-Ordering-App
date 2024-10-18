package com.example.foodorderingapp.Fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodorderingapp.CongratsBottomSheet
import com.example.foodorderingapp.PayoutActivity
import com.example.foodorderingapp.R
import com.example.foodorderingapp.adapter.CartAdapter
import com.example.foodorderingapp.databinding.FragmentCartBinding
import com.example.foodorderingapp.databinding.FragmentHomeBinding

class CartFragment : Fragment() {
   private lateinit var binding: FragmentCartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartBinding.inflate(inflater, container, false)

        val cartFoodName = listOf("Burger", "Sandwitch", "momo", "Burger", "Item")
        var foodPrice = listOf("$5", "$10", "$8", "$9", "$8")
        var foodImages = listOf(
            R.drawable.menubreakfast,
            R.drawable.menusalad,
            R.drawable.menufruits,
            R.drawable.menubreakfast,
            R.drawable.menusalad
        )
        val adapter = CartAdapter(ArrayList(cartFoodName), ArrayList(foodPrice), ArrayList(foodImages))
        binding.cartRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.cartRecyclerView.adapter = adapter

        binding.proceedButton.setOnClickListener {
            val intent = Intent(requireContext(), PayoutActivity::class.java)
            startActivity(intent)
        }

        return binding.root
    }
}