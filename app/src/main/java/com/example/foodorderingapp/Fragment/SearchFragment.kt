package com.example.foodorderingapp.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodorderingapp.R
import com.example.foodorderingapp.adapter.MenuAdapter
import com.example.foodorderingapp.databinding.FragmentHomeBinding
import com.example.foodorderingapp.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private lateinit var adapter: MenuAdapter
     val originalFoodMenuName = listOf("Burger", "Sandwitch", "momo", "Burger", "Item", "Burger", "Sandwitch", "momo", "Burger", "Item")
    private val originalFoodPrice = listOf("$5", "$10", "$8", "$9", "$8", "$5", "$10", "$8", "$9", "$8")
    private val originalItemImage = listOf(
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private val filterMenuFoodName = mutableListOf<String>()
    private val filterMenuFoodPrice = mutableListOf<String>()
    private val filterMenuFoodImage = mutableListOf<Int>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        adapter = MenuAdapter(filterMenuFoodName, filterMenuFoodPrice, filterMenuFoodImage)
        binding.menuRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.menuRecyclerView.adapter = adapter

        //setup for search view
        setUpSearchView()

        //show all menu items
        showAllMenuItems()

        return binding.root
    }

    private fun showAllMenuItems() {
        filterMenuFoodName.clear()
        filterMenuFoodPrice.clear()
        filterMenuFoodImage.clear()

        filterMenuFoodName.addAll(originalFoodMenuName)
        filterMenuFoodPrice.addAll(originalFoodPrice)
        filterMenuFoodImage.addAll(originalItemImage)
    }

    private fun setUpSearchView() {
     binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
         override fun onQueryTextSubmit(query: String): Boolean {
             filterMenuItems(query)
             return true
         }

         override fun onQueryTextChange(newText: String?): Boolean {
            filterMenuItems(newText)
            return true
         }
     })
    }

    private fun filterMenuItems(query: String?) {
        filterMenuFoodName.clear()
        filterMenuFoodPrice.clear()
        filterMenuFoodImage.clear()

        originalFoodMenuName.forEachIndexed { index, foodName ->
            if(foodName.contains(query.toString(), ignoreCase = true)) {
                filterMenuFoodName.add(foodName)
                filterMenuFoodPrice.add(originalFoodPrice[index])
                filterMenuFoodImage.add(originalItemImage[index])
            }
        }

        adapter.notifyDataSetChanged()
    }

    companion object {

    }
}