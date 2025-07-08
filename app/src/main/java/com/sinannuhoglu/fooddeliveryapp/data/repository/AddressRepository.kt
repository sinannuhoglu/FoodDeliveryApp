package com.sinannuhoglu.fooddeliveryapp.data.repository

import android.content.Context
import android.location.Address
import android.location.Geocoder
import com.google.android.gms.maps.model.LatLng
import java.util.Locale

class AddressRepository(private val context: Context) {
    fun getAddressFromLatLng(latLng: LatLng): String {
        return try {
            val geocoder = Geocoder(context, Locale.getDefault())
            val addresses: List<Address> =
                geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1) ?: emptyList()
            if (addresses.isNotEmpty()) {
                addresses[0].getAddressLine(0) ?: "Adres bulunamadı"
            } else {
                "Adres bulunamadı"
            }
        } catch (e: Exception) {
            "Adres alınamadı"
        }
    }
}
