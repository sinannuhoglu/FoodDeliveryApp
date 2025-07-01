package com.sinannuhoglu.fooddeliveryapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.sinannuhoglu.fooddeliveryapp.R

@Composable
fun TotalPriceSection(quantity: Int, price: Int, onAddToBasket: () -> Unit) {
    val total = quantity * price
    val mainColor = colorResource(id = R.color.mainColor)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(text = "Adet: $quantity", color = Color.White, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Toplam: ${total}₺",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
        Button(
            onClick = onAddToBasket,
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            modifier = Modifier.padding(start = 16.dp)
        ) {
            Text("Sepete Ekle", color = mainColor, fontWeight = FontWeight.Bold)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TotalPriceSectionPreview() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black)
            .padding(16.dp)
    ) {
        TotalPriceSection(quantity = 2, price = 50, onAddToBasket = {})
    }
}
