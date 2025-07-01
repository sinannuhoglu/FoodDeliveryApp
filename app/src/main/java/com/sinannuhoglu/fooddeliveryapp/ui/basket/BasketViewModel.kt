package com.sinannuhoglu.fooddeliveryapp.ui.basket

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sinannuhoglu.fooddeliveryapp.model.BasketItem
import com.sinannuhoglu.fooddeliveryapp.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor(
    private val apiService: ApiService
) : ViewModel() {

    private val _basketItems = MutableStateFlow<List<BasketItem>>(emptyList())
    val basketItems: StateFlow<List<BasketItem>> = _basketItems

    /**
     * Sepete yeni ürün ekler ve API'ye gönderir.
     */
    fun addItemToBasket(item: BasketItem) {
        viewModelScope.launch {
            try {
                val response = apiService.addToCart(
                    yemekAdi = item.yemek_adi,
                    yemekResimAdi = item.yemek_resim_adi,
                    yemekFiyat = item.yemek_fiyat.toIntOrNull() ?: 0,
                    yemekSiparisAdet = item.yemek_siparis_adet.toIntOrNull() ?: 1,
                    kullaniciAdi = item.kullanici_adi
                )
                if (response.isSuccessful) {
                    val updatedList = _basketItems.value.toMutableList().apply { add(item) }
                    _basketItems.value = updatedList
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    /**
     * Sepetten ürün siler ve API'den kaldırır.
     */
    fun removeItemFromBasket(item: BasketItem) {
        viewModelScope.launch {
            try {
                val response = apiService.deleteFromBasket(
                    sepetYemekId = item.sepet_yemek_id.toIntOrNull() ?: 0,
                    kullaniciAdi = item.kullanici_adi
                )
                if (response.isSuccessful) {
                    val updatedList = _basketItems.value.toMutableList().apply { remove(item) }
                    _basketItems.value = updatedList
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    /**
     * Sepetteki tüm ürünleri çeker.
     */
    fun getBasketItems(kullaniciAdi: String) {
        viewModelScope.launch {
            try {
                val response = apiService.getBasketItems(kullaniciAdi)
                if (response.isSuccessful) {
                    val items = response.body()?.sepet_yemekler ?: emptyList()
                    _basketItems.value = items
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
