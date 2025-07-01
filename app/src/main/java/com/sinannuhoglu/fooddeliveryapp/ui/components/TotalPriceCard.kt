package com.sinannuhoglu.fooddeliveryapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.sinannuhoglu.fooddeliveryapp.R

@Composable
fun TotalPriceCard(quantity: Int, price: Int, onAddToBasket: () -> Unit) {
    val mainColor = colorResource(id = R.color.mainColor)
    val mainLightColor = colorResource(id = R.color.mainLightColor)
    val gradient = Brush.verticalGradient(colors = listOf(mainColor, mainLightColor))
    val total = quantity * price

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(brush = gradient, shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
            .padding(24.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "Adet: $quantity", color = Color.White)
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Toplam: ${total}₺",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = { onAddToBasket() },
                colors = ButtonDefaults.buttonColors(containerColor = Color.White)
            ) {
                Text("Sepete Ekle", color = mainColor)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TotalPriceCardPreview() {
    TotalPriceCard(quantity = 2, price = 50, onAddToBasket = {
    })
}
