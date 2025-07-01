package com.sinannuhoglu.fooddeliveryapp.model

import com.google.gson.annotations.SerializedName

data class FoodResponse(
    @SerializedName("success")
    val success: Int?,
    @SerializedName("yemekler")
    val yemekler: List<Yemekler?>?
)