package com.sinannuhoglu.fooddeliveryapp.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.sinannuhoglu.fooddeliveryapp.model.FoodItem

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreenContent(
        foodList = listOf(
            FoodItem(
                id = 1,
                name = "Baklava",
                price = 250,
                imageUrl = "http://kasimadalan.pe.hu/yemekler/resimler/baklava.png",
                isFavorite = true
            ),
            FoodItem(
                id = 2,
                name = "Ayran",
                price = 30,
                imageUrl = "http://kasimadalan.pe.hu/yemekler/resimler/ayran.png",
                isFavorite = false
            )
        ),
        searchQuery = "",
        onSearchQueryChanged = {},
        onFavoriteClick = {},
        onItemClick = {}
    )
}
