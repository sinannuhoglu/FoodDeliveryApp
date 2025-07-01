package com.sinannuhoglu.fooddeliveryapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sinannuhoglu.fooddeliveryapp.model.FoodItem
import coil.compose.AsyncImage
import com.sinannuhoglu.fooddeliveryapp.R

@Composable
fun FoodItemCard(
    item: FoodItem,
    onFavoriteClick: (FoodItem) -> Unit,
    onItemClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onItemClick,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = modifier
            .width(160.dp)
            .height(220.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            IconButton(
                onClick = {
                    onFavoriteClick(item)
                },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(8.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {}
            ) {
                Icon(
                    imageVector = if (item.isFavorite) Icons.Filled.Favorite else Icons.Outlined.Favorite,
                    contentDescription = "Favori",
                    tint = if (item.isFavorite) colorResource(id = R.color.mainColor) else Color.Gray,
                    modifier = Modifier.size(32.dp)
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Spacer(modifier = Modifier.height(8.dp))

                AsyncImage(
                    model = item.imageUrl,
                    contentDescription = item.name,
                    modifier = Modifier.size(100.dp)
                )

                Text(
                    text = item.name,
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    text = "Ücretsiz Gönderim",
                    style = MaterialTheme.typography.bodySmall.copy(fontSize = 10.sp)
                )

                Text(
                    text = "${item.price} ₺",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewFoodItemCard() {
    val sampleItem = FoodItem(
        id = 1,
        name = "Pizza Margherita",
        imageUrl = "https://via.placeholder.com/100",
        price = 150,
        isFavorite = true
    )

    FoodItemCard(
        item = sampleItem,
        onFavoriteClick = { },
        onItemClick = { }
    )
}
