package com.example.realestate

data class Item(
    val category: String,
    val name: String,
    var quantity: Int,
    val imageResId: Int,
    val price: String,
    val unit: String,
    val priceLow: String,
    val priceHigh: String,
    val jayaPrice: String,
    val vgPrice: String,
    val lotusPrice: String,
    val bigPrice: String,
    val aeonPrice: String
)

