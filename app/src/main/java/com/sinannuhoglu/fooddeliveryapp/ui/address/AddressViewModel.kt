package com.sinannuhoglu.fooddeliveryapp.ui.address

import android.app.Application
import android.content.pm.PackageManager
import android.location.Location
import androidx.core.app.ActivityCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import com.sinannuhoglu.fooddeliveryapp.data.repository.AddressRepository
import com.sinannuhoglu.fooddeliveryapp.model.BasketItem
import com.sinannuhoglu.fooddeliveryapp.ui.delivery.DeliveryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AddressViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = AddressRepository(application.applicationContext)
    private val deliveryRepository = DeliveryRepository()

    private val _addressState = MutableStateFlow(AddressState())
    val addressState: StateFlow<AddressState> = _addressState

    private val _deliverySuccess = MutableStateFlow<Boolean?>(null)
    val deliverySuccess: StateFlow<Boolean?> = _deliverySuccess

    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(application)

    fun fetchCurrentLocation() {
        val context = getApplication<Application>().applicationContext

        if (ActivityCompat.checkSelfPermission(
                context,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
                location?.let {
                    val latLng = LatLng(it.latitude, it.longitude)
                    _addressState.value = _addressState.value.copy(selectedLatLng = latLng)
                }
            }
        }
    }

    fun setAddressFromLatLng(latLng: LatLng) {
        viewModelScope.launch {
            val addr = repository.getAddressFromLatLng(latLng)
            _addressState.value = _addressState.value.copy(address = addr)
        }
    }

    fun updateAddress(newAddress: String) {
        _addressState.value = _addressState.value.copy(address = newAddress)
    }

    fun updateDetail(newDetail: String) {
        _addressState.value = _addressState.value.copy(detail = newDetail)
    }

    fun submitOrder(items: List<BasketItem>, userEmail: String) {
        viewModelScope.launch {
            val success = deliveryRepository.submitOrder(
                items = items,
                address = _addressState.value.address,
                detail = _addressState.value.detail,
                userEmail = userEmail
            )
            _deliverySuccess.value = success
        }
    }

    fun resetDeliverySuccess() {
        _deliverySuccess.value = null
    }
}
