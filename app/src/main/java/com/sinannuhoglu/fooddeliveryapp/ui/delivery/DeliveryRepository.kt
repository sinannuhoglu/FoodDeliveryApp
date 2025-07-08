package com.sinannuhoglu.fooddeliveryapp.ui.delivery

import com.google.firebase.database.FirebaseDatabase
import com.sinannuhoglu.fooddeliveryapp.model.BasketItem
import kotlinx.coroutines.tasks.await

class DeliveryRepository {

    private val database = FirebaseDatabase.getInstance()

    suspend fun submitOrder(
        items: List<BasketItem>,
        address: String,
        detail: String,
        userEmail: String
    ): Boolean {
        return try {
            val itemList = items.map {
                val price = it.yemek_fiyat.toIntOrNull() ?: 0
                val quantity = it.yemek_siparis_adet.toIntOrNull() ?: 1
                mapOf("name" to it.yemek_adi, "price" to price, "quantity" to quantity)
            }
            val totalPrice = items.sumOf {
                (it.yemek_fiyat.toIntOrNull() ?: 0) * (it.yemek_siparis_adet.toIntOrNull() ?: 1)
            }
            val orderData = mapOf(
                "items" to itemList,
                "address" to address,
                "detail" to detail,
                "userEmail" to userEmail,
                "totalPrice" to totalPrice,
                "timestamp" to System.currentTimeMillis()
            )
            val ref = database.getReference("orders")
            ref.push().setValue(orderData).await()
            true
        } catch (e: Exception) {
            false
        }
    }
}
