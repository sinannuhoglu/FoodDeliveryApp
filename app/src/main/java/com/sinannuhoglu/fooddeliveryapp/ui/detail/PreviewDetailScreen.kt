package com.sinannuhoglu.fooddeliveryapp.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.sinannuhoglu.fooddeliveryapp.model.FoodItem
import com.sinannuhoglu.fooddeliveryapp.ui.components.FoodInfoSection
import com.sinannuhoglu.fooddeliveryapp.ui.components.QuantitySelector
import com.sinannuhoglu.fooddeliveryapp.ui.theme.UiConstants

@Preview(showBackground = true)
@Composable
fun PreviewDetailScreen() {
    val mockFood = FoodItem(
        id = 1,
        name = "Margherita Pizza",
        imageUrl = "https://loremflickr.com/320/240/pizza",
        price = 150,
        isFavorite = true
    )

    val quantity = 2

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = UiConstants.gradientBackground)
            .padding(horizontal = UiConstants.defaultPadding),
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
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.TopCenter
        ) {
            AsyncImage(
                model = mockFood.imageUrl,
                contentDescription = mockFood.name,
                modifier = Modifier
                    .size(300.dp)
                    .padding(top = 32.dp)
            )
            IconButton(
                onClick = { /* Toggle favorite example */ },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = UiConstants.smallPadding, end = 32.dp)
            ) {
                Icon(
                    imageVector = if (mockFood.isFavorite) Icons.Filled.Favorite else Icons.Outlined.Favorite,
                    contentDescription = "Favori",
                    tint = if (mockFood.isFavorite) Color(0xFFFE347C) else Color.Gray,
                    modifier = Modifier.size(32.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(UiConstants.spacerHeight))
        FoodInfoSection(name = mockFood.name)
        Spacer(modifier = Modifier.height(UiConstants.spacerHeight))
        QuantitySelector(
            price = mockFood.price,
            quantity = quantity,
            onDecrease = { /* Decrease */ },
            onIncrease = { /* Increase */ }
        )
        Spacer(modifier = Modifier.height(80.dp))
    }
}
