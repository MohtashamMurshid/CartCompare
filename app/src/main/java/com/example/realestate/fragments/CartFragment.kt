package com.example.realestate.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.realestate.CartAdapter
import com.example.realestate.R
import com.example.realestate.SharedCartViewModel

class CartFragment : Fragment() {

    private val sharedViewModel: SharedCartViewModel by activityViewModels()
    private lateinit var cartAdapter: CartAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)



        // Initialize the adapter with only onRemoveItem callback
        cartAdapter = CartAdapter(
            onRemoveItem = { removedItem ->
                sharedViewModel.removeItem(removedItem) // Remove the item from ViewModel
                Log.d("CartFragment", "Removed item: ${removedItem.name}")
            }
        )
        recyclerView.adapter = cartAdapter


        // Observe cart items from ViewModel
        sharedViewModel.cartItems.observe(viewLifecycleOwner) { cartItems ->
            cartAdapter.submitList(cartItems.toList())
            Log.d("CartFragment", "Cart items updated: ${cartItems.size}")
            cartItems.forEach {
                Log.d("CartFragment", "Item: ${it.name}, Quantity: ${it.quantity}")
            }
        }

    }
}