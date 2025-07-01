package com.sinannuhoglu.fooddeliveryapp.model

data class FoodItem(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val price: Int,
    var isFavorite: Boolean
)
