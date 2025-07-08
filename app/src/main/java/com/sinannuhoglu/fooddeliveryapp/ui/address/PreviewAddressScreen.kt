package com.sinannuhoglu.fooddeliveryapp.ui.address

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.sinannuhoglu.fooddeliveryapp.ui.components.AddressTextField
import com.sinannuhoglu.fooddeliveryapp.ui.components.ConfirmButton
import com.sinannuhoglu.fooddeliveryapp.ui.theme.UiConstants

@Preview(showBackground = true)
@Composable
fun PreviewAddressScreen() {
    val dummyNavController = rememberNavController()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = UiConstants.gradientBackground),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(UiConstants.spacerHeight * 2))
        Text(
            text = "Bir Tıkla Sofranda",
            style = MaterialTheme.typography.bodySmall.copy(fontSize = UiConstants.headerFontSize),
            color = UiConstants.mainGrayTextColor
        )
        Spacer(modifier = Modifier.height(UiConstants.spacerHeight * 1.5f))

        Box(
            modifier = Modifier
                .height(UiConstants.mapHeight * 1.6f)
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
                .clip(RoundedCornerShape(UiConstants.cornerRadius))
                .background(Color.Gray),
            contentAlignment = Alignment.Center
        ) {
            Text("Harita Önizleme", color = Color.White)
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
                AddressTextField(value = "Googleplex", onValueChange = {}, label = "Adres")
                Spacer(modifier = Modifier.height(UiConstants.smallPadding))
                AddressTextField(
                    value = "Kat 3, Daire 5",
                    onValueChange = {},
                    label = "Adres Detayı (ör. daire no)"
                )

                Spacer(modifier = Modifier.height(UiConstants.spacerHeight))

                ConfirmButton {}
            }
        }
    }
}