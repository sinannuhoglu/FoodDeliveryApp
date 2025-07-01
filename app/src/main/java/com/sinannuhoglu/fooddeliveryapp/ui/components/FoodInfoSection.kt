package com.sinannuhoglu.fooddeliveryapp.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun FoodInfoSection(
    name: String,
    description: String = "Ücretsiz Gönderim",
    nameFontSize: Int = 24,
    nameFontWeight: FontWeight = FontWeight.Bold,
    descriptionFontSize: Int = 16,
    descriptionFontWeight: FontWeight = FontWeight.Normal
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = name,
            fontSize = nameFontSize.sp,
            fontWeight = nameFontWeight
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = description,
            fontSize = descriptionFontSize.sp,
            fontWeight = descriptionFontWeight
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FoodInfoSectionPreview() {
    FoodInfoSection(
        name = "Baklava",
        description = "Free Shipping",
        nameFontSize = 30,
        descriptionFontSize = 18
    )
}
