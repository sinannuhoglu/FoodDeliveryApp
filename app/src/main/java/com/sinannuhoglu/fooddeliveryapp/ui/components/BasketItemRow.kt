package com.sinannuhoglu.fooddeliveryapp.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.sinannuhoglu.fooddeliveryapp.R
import com.sinannuhoglu.fooddeliveryapp.model.BasketItem

@Composable
fun BasketItemRow(item: BasketItem, onRemove: (BasketItem) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.weight(1f)
        ) {
            AsyncImage(
                model = item.yemek_resim_adi,
                contentDescription = item.yemek_adi,
                modifier = Modifier.size(64.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(text = item.yemek_adi, style = MaterialTheme.typography.bodyMedium)
                Text(text = "Adet: ${item.yemek_siparis_adet}", style = MaterialTheme.typography.bodySmall)
                Text(
                    text = "${(item.yemek_fiyat.toIntOrNull() ?: 0) * (item.yemek_siparis_adet.toIntOrNull() ?: 1)} ₺",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

        IconButton(
            onClick = { onRemove(item) },
            modifier = Modifier.padding(start = 8.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Sil",
                tint = colorResource(id = R.color.mainColor)
            )
        }
    }
}
