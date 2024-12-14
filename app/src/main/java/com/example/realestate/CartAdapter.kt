package com.example.realestate

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class CartAdapter(
    private val onRemoveItem: (Item) -> Unit
) : ListAdapter<Item, CartAdapter.CartViewHolder>(DiffCallback()) {

    class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val itemImage: ImageView = itemView.findViewById(R.id.imageView)
        private val itemName: TextView = itemView.findViewById(R.id.itemName)
        private val minusItem: ImageView = itemView.findViewById(R.id.minusItem)
        private val numText: TextView = itemView.findViewById(R.id.holderText1)

        fun bind(item: Item, onRemoveItem: (Item) -> Unit) {
            // Set item details
            itemImage.setImageResource(item.imageResId)
            itemName.text = item.name
            numText.text = item.quantity.toString()

            // Remove item when minus button is clicked
            minusItem.setOnClickListener {
                onRemoveItem(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.secondholder_cart, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(getItem(position), onRemoveItem)
    }

    class DiffCallback : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }
    }
}