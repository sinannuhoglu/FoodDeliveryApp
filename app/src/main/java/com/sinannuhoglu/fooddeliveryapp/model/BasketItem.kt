package com.sinannuhoglu.fooddeliveryapp.model

data class BasketItem(
    val sepet_yemek_id: String,
    val yemek_adi: String,
    val yemek_resim_adi: String,
    val yemek_fiyat: String,
    val yemek_siparis_adet: String,
    val kullanici_adi: String
)