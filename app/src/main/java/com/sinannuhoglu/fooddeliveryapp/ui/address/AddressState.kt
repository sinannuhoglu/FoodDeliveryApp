package com.sinannuhoglu.fooddeliveryapp.ui.address

import com.google.android.gms.maps.model.LatLng

data class AddressState(
    val selectedLatLng: LatLng? = null,
    val address: String = "",
    val detail: String = ""
)
