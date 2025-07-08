package com.sinannuhoglu.fooddeliveryapp.ui.address

import android.Manifest
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.permissions.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.maps.android.compose.*
import com.sinannuhoglu.fooddeliveryapp.ui.basket.BasketViewModel
import com.sinannuhoglu.fooddeliveryapp.ui.components.AddressTextField
import com.sinannuhoglu.fooddeliveryapp.ui.components.ConfirmButton
import com.sinannuhoglu.fooddeliveryapp.ui.theme.UiConstants
import com.sinannuhoglu.fooddeliveryapp.util.UserSession

@OptIn(ExperimentalPermissionsApi::class, ExperimentalMaterial3Api::class)
@Composable
fun AddressScreen(
    navController: NavController,
    basketViewModel: BasketViewModel,
    viewModel: AddressViewModel = hiltViewModel()
) {
    val state by viewModel.addressState.collectAsState()
    val deliverySuccess by viewModel.deliverySuccess.collectAsState()

    val cameraPositionState = rememberCameraPositionState()
    val locationPermissionState = rememberPermissionState(Manifest.permission.ACCESS_FINE_LOCATION)

    val items by basketViewModel.basketItems.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    val userEmail = UserSession.email ?: "unknown@example.com"

    LaunchedEffect(Unit) {
        locationPermissionState.launchPermissionRequest()
    }

    if (locationPermissionState.status is PermissionStatus.Granted) {
        LaunchedEffect(Unit) {
            viewModel.fetchCurrentLocation()
        }
    }

    LaunchedEffect(state.selectedLatLng) {
        state.selectedLatLng?.let {
            cameraPositionState.animate(CameraUpdateFactory.newLatLngZoom(it, 15f))
            viewModel.setAddressFromLatLng(it)
        }
    }

    LaunchedEffect(deliverySuccess) {
        if (deliverySuccess == true) {
            snackbarHostState.showSnackbar("Siparişiniz alınmıştır")
            basketViewModel.clearBasket()
            navController.navigate("home") {
                popUpTo(navController.graph.startDestinationId) { inclusive = false }
                launchSingleTop = true
            }
            viewModel.resetDeliverySuccess()
        }
    }

    Scaffold(snackbarHost = {
        SnackbarHost(
            snackbarHostState,
            modifier = Modifier.padding(bottom = UiConstants.snackbarPadding)
        )
    }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = UiConstants.gradientBackground)
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(UiConstants.spacerHeight))
            Text(
                text = "Bir Tıkla Sofranda",
                style = MaterialTheme.typography.bodySmall.copy(fontSize = UiConstants.headerFontSize),
                color = UiConstants.mainGrayTextColor
            )
            Spacer(modifier = Modifier.height(UiConstants.spacerHeight))

            Box(
                modifier = Modifier
                    .height(UiConstants.mapHeight * 1.6f)
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
                    .clip(RoundedCornerShape(UiConstants.cornerRadius))
                    .background(Color.LightGray)
            ) {
                GoogleMap(
                    modifier = Modifier.fillMaxSize(),
                    cameraPositionState = cameraPositionState,
                    properties = MapProperties(isMyLocationEnabled = locationPermissionState.status is PermissionStatus.Granted),
                ) {
                    state.selectedLatLng?.let {
                        Marker(state = MarkerState(position = it), title = "Konumunuz")
                    }
                }
            }

            Spacer(modifier = Modifier.height(UiConstants.spacerHeight))

            Card(
                shape = RoundedCornerShape(
                    topStart = 24.dp,
                    topEnd = 24.dp,
                    bottomStart = 0.dp,
                    bottomEnd = 0.dp
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    AddressTextField(
                        value = state.address,
                        onValueChange = { viewModel.updateAddress(it) },
                        label = "Adres"
                    )
                    Spacer(modifier = Modifier.height(UiConstants.smallPadding))
                    AddressTextField(
                        value = state.detail,
                        onValueChange = { viewModel.updateDetail(it) },
                        label = "Adres Detayı (ör. daire no)"
                    )

                    Spacer(modifier = Modifier.height(UiConstants.spacerHeight))

                    ConfirmButton {
                        viewModel.submitOrder(items, userEmail)
                    }
                }
            }
        }
    }
}
