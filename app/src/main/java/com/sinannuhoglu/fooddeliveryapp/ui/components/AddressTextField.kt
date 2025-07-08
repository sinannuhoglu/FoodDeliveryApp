package com.sinannuhoglu.fooddeliveryapp.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AddressTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String
) {
    val leadingIcon = when (label) {
        "Adres" -> Icons.Filled.Place
        else -> Icons.Filled.Home
    }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = Modifier.fillMaxWidth(),
        leadingIcon = {
            Icon(imageVector = leadingIcon, contentDescription = null)
        },
        singleLine = true
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewAddressTextField() {
    val addressState = remember { mutableStateOf("Google Building 43") }
    val detailState = remember { mutableStateOf("Kat 3, Daire 5") }

    Column(modifier = Modifier.fillMaxWidth()) {
        AddressTextField(
            value = addressState.value,
            onValueChange = { addressState.value = it },
            label = "Adres"
        )
        Spacer(modifier = Modifier.height(16.dp))
        AddressTextField(
            value = detailState.value,
            onValueChange = { detailState.value = it },
            label = "Adres Detayı (ör. daire no)"
        )
    }
}
