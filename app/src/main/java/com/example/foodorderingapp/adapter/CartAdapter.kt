package com.example.foodorderingapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.example.foodorderingapp.databinding.CartItemBinding

class CartAdapter(
    private val cartItems: MutableList<String>,
    private val cartItemsPrice: MutableList<String>,
    private val cartImages: MutableList<Int>
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {
    private val itemQuantities = IntArray(cartImages.size){1}

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): CartViewHolder {
        val binding = CartItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: CartViewHolder,
        position: Int,
    ) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = cartItems.size

    inner class CartViewHolder(
        private val binding: CartItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.apply {
                val itemquantity = itemQuantities[position]
                cartFoodName.text = cartItems[position]
                cartItemPrice.text = cartItemsPrice[position]
                cartImage.setImageResource(cartImages[position])
                quantity.text = itemquantity.toString()

                minusButton.setOnClickListener {
        decreaseQuantity(position)
                }

                plusButton.setOnClickListener {
  increaseQuantity(position)
                }
            }
        }

        private fun decreaseQuantity(position: Int) {
            if (itemQuantities[position] >1) {
                itemQuantities[position]--
                binding.quantity.text = itemQuantities[position].toString()
            }

            if(itemQuantities[position] <= 1) {
                val itemPosition = adapterPosition
                if (itemPosition!= RecyclerView.NO_POSITION) {
                    deleteItem(itemPosition)
                }
            }
        }

        fun increaseQuantity(position: Int) {
            if (itemQuantities[position] <10) {
                itemQuantities[position]++
                binding.quantity.text = itemQuantities[position].toString()
            }
        }

        fun deleteItem(position: Int) {
            cartItems.removeAt(position)
            cartItemsPrice.removeAt(position)
            cartImages.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, cartItems.size)
        }
    }
}
