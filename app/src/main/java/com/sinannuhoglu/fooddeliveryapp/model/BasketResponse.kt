package com.sinannuhoglu.fooddeliveryapp.model

data class BasketResponse(
    val sepet_yemekler: List<BasketItem>,
    val success: Int
)