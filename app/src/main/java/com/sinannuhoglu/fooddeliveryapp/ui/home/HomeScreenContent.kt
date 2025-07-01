package com.sinannuhoglu.fooddeliveryapp.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sinannuhoglu.fooddeliveryapp.model.FoodItem
import com.sinannuhoglu.fooddeliveryapp.ui.components.FoodItemCard

@Composable
fun HomeScreenContent(
    foodList: List<FoodItem>,
    searchQuery: String,
    onSearchQueryChanged: (String) -> Unit,
    onItemClick: (FoodItem) -> Unit,
    onFavoriteClick: (FoodItem) -> Unit
) {
    val gradientBrush = Brush.verticalGradient(
        colors = listOf(
            Color.White,
            Color(0xFFD3D3D3)
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = gradientBrush)
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Bir Tıkla Sofranda",
            style = MaterialTheme.typography.bodySmall.copy(fontSize = 10.sp),
            color = Color.Gray,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = searchQuery,
            onValueChange = onSearchQueryChanged,
            placeholder = { Text("Ara", color = Color.Gray) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFFFE347C),
                unfocusedBorderColor = Color.Gray
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 80.dp)
        ) {
            items(foodList) { item ->
                FoodItemCard(
                    item = item,
                    onFavoriteClick = { onFavoriteClick(item) },
                    onItemClick = { onItemClick(item) }
                )
            }
        }
    }
}
