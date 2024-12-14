package com.example.realestate

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedCartViewModel : ViewModel() {

    private val _cartItems = MutableLiveData<MutableList<Item>>(mutableListOf())
    val cartItems: LiveData<MutableList<Item>> get() = _cartItems

    // Add an item to the cart
    fun addItem(item: Item) {
        val cartList = _cartItems.value?.toMutableList() ?: mutableListOf()
        val existingItem = cartList.find { it.name == item.name }

        if (existingItem != null) {
            // Update the quantity of the existing item
            existingItem.quantity += item.quantity
            Log.d("SharedCartViewModel", "Updated item quantity: ${item.name} -> ${existingItem.quantity}")
        } else {
            // Clone the item before adding it to the cart
            val newItem = item.copy()
            cartList.add(newItem)
            Log.d("SharedCartViewModel", "Added new item: ${item.name}")
        }

        _cartItems.value = cartList // Update LiveData
    }

    // Remove an item from the cart
    fun removeItem(item: Item) {
        val cartList = _cartItems.value?.toMutableList() ?: mutableListOf()
        cartList.removeAll { it.name == item.name }
        Log.d("SharedCartViewModel", "Removed item: ${item.name}")

        _cartItems.value = cartList // Update LiveData
    }

    // Clear all items in the cart
    fun clearCart() {
        _cartItems.value = mutableListOf()
        Log.d("SharedCartViewModel", "Cart cleared")
    }
}





