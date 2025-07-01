package com.sinannuhoglu.fooddeliveryapp.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun FoodImage(
    imageUrl: String,
    name: String,
    modifier: Modifier = Modifier,
    size: Int = 200,
    placeholderRes: Int? = null
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .placeholder(placeholderRes ?: android.R.drawable.ic_menu_report_image)
            .error(placeholderRes ?: android.R.drawable.ic_menu_report_image)
            .build(),
        contentDescription = name,
        modifier = modifier
            .size(size.dp)
            .padding(8.dp),
        contentScale = ContentScale.Crop
    )
}

@Preview(showBackground = true)
@Composable
fun FoodImagePreview() {
    FoodImage(
        imageUrl = "https://example.com/food_image.jpg",
        name = "Örnek Yemek",
        size = 150
    )
}
