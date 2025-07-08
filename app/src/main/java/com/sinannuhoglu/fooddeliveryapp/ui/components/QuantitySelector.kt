package com.sinannuhoglu.fooddeliveryapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sinannuhoglu.fooddeliveryapp.R

@Composable
fun QuantitySelector(
    price: Int,
    quantity: Int,
    onDecrease: () -> Unit,
    onIncrease: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .background(androidx.compose.ui.graphics.Color.White, shape = RoundedCornerShape(50.dp))
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        IconButton(
            onClick = onDecrease,
            modifier = Modifier.padding(end = 8.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_remove),
                contentDescription = "Azalt",
                tint = colorResource(id = R.color.mainColor)
            )
        }

        Text(
            text = "$quantity x $price ₺",
            fontSize = 18.sp,
            modifier = Modifier.padding(horizontal = 8.dp)
        )

        IconButton(
            onClick = onIncrease,
            modifier = Modifier.padding(start = 8.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_add),
                contentDescription = "Arttır",
                tint = colorResource(id = R.color.mainColor)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun QuantitySelectorPreview() {
    var quantity by remember { mutableStateOf(1) }
    val pricePerItem = 50
    val totalPrice = pricePerItem * quantity

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        QuantitySelector(
            price = pricePerItem,
            quantity = quantity,
            onDecrease = { if (quantity > 1) quantity-- },
            onIncrease = { quantity++ }
        )
    }
}
