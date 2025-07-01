package com.sinannuhoglu.fooddeliveryapp.ui.detail

import androidx.lifecycle.ViewModel
import com.sinannuhoglu.fooddeliveryapp.model.BasketItem
import com.sinannuhoglu.fooddeliveryapp.model.FoodItem
import com.sinannuhoglu.fooddeliveryapp.ui.basket.BasketViewModel
import com.sinannuhoglu.fooddeliveryapp.util.UserSession
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DetailViewModel : ViewModel() {

    private val _foodItem = MutableStateFlow<FoodItem?>(null)
    val foodItem: StateFlow<FoodItem?> get() = _foodItem

    private val _quantity = MutableStateFlow(1)
    val quantity: StateFlow<Int> get() = _quantity

    /**
     * Seçilen FoodItem'ı ayarlar
     */
    fun setFoodItem(item: FoodItem) {
        _foodItem.value = item
    }

    /**
     * Ürün miktarını artırır
     */
    fun increaseQuantity() {
        _quantity.value++
    }

    /**
     * Ürün miktarını azaltır (1'in altına düşmez)
     */
    fun decreaseQuantity() {
        if (_quantity.value > 1) _quantity.value--
    }

    /**
     * Sepete ürün ekler
     */
    fun addToBasket(basketViewModel: BasketViewModel) {
        _foodItem.value?.let { food ->
            val adet = _quantity.value
            val basketItem = BasketItem(
                sepet_yemek_id = "0",
                yemek_adi = food.name,
                yemek_resim_adi = food.imageUrl,
                yemek_fiyat = food.price.toString(),
                yemek_siparis_adet = adet.toString(),
                kullanici_adi = UserSession.email ?: "default@mail.com"
            )
            basketViewModel.addItemToBasket(basketItem)
        }
    }
}
