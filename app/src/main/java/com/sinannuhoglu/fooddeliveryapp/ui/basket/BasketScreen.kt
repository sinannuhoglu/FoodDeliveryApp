package com.sinannuhoglu.fooddeliveryapp.ui.basket

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.sinannuhoglu.fooddeliveryapp.ui.components.BasketItemRow
import com.sinannuhoglu.fooddeliveryapp.ui.components.OrderSummaryCard
import com.sinannuhoglu.fooddeliveryapp.ui.bottomnav.BottomBar
import com.sinannuhoglu.fooddeliveryapp.navigation.Routes
import com.sinannuhoglu.fooddeliveryapp.ui.theme.UiConstants
import kotlinx.coroutines.launch

@Composable
fun BasketScreen(
    navController: NavController,
    viewModel: BasketViewModel
) {
    val items by viewModel.basketItems.collectAsState()

    val totalPrice = items.sumOf {
        (it.yemek_fiyat.toIntOrNull() ?: 0) * (it.yemek_siparis_adet.toIntOrNull() ?: 1)
    }

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        bottomBar = { BottomBar(navController = navController) },
        snackbarHost = {
            SnackbarHost(
                snackbarHostState,
                modifier = Modifier.padding(bottom = UiConstants.snackbarPadding)
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = UiConstants.gradientBackground)
                .padding(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(UiConstants.spacerHeight))
            Text(
                text = "Bir Tıkla Sofranda",
                style = MaterialTheme.typography.bodySmall.copy(fontSize = UiConstants.headerFontSize),
                color = UiConstants.mainGrayTextColor
            )
            Spacer(modifier = Modifier.height(UiConstants.spacerHeight))

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
                if (items.isEmpty() || totalPrice == 0) {
                    scope.launch {
                        snackbarHostState.showSnackbar("Sepetiniz boş!")
                    }
                } else {
                    navController.navigate(Routes.ADDRESS)
                }
            }
        }
    }
}
