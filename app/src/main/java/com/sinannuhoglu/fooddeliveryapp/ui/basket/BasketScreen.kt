package com.sinannuhoglu.fooddeliveryapp.ui.basket

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sinannuhoglu.fooddeliveryapp.ui.components.BasketItemRow
import com.sinannuhoglu.fooddeliveryapp.ui.components.OrderSummaryCard
import com.sinannuhoglu.fooddeliveryapp.ui.bottomnav.BottomBar
import androidx.compose.ui.Alignment

@Composable
fun BasketScreen(
    navController: NavController,
    viewModel: BasketViewModel
) {
    val context = LocalContext.current
    val items by viewModel.basketItems.collectAsState()

    val totalPrice = items.sumOf {
        (it.yemek_fiyat.toIntOrNull() ?: 0) * (it.yemek_siparis_adet.toIntOrNull() ?: 1)
    }

    val gradientBrush = Brush.verticalGradient(
        colors = listOf(Color.White, Color(0xFFD3D3D3))
    )

    Scaffold(
        bottomBar = { BottomBar(navController = navController) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = gradientBrush)
                .padding(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Bir Tıkla Sofranda",
                style = MaterialTheme.typography.bodySmall.copy(fontSize = 10.sp),
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(items) { item ->
                    BasketItemRow(
                        item = item,
                        onRemove = { viewModel.removeItemFromBasket(it) }
                    )
                    Divider()
                }
            }

            OrderSummaryCard(totalPrice = totalPrice) {
                Toast.makeText(context, "Siparişiniz alındı!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
