package com.sinannuhoglu.fooddeliveryapp.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.sinannuhoglu.fooddeliveryapp.R
import com.sinannuhoglu.fooddeliveryapp.model.FoodItem
import com.sinannuhoglu.fooddeliveryapp.ui.basket.BasketViewModel
import com.sinannuhoglu.fooddeliveryapp.ui.components.FoodInfoSection
import com.sinannuhoglu.fooddeliveryapp.ui.components.QuantitySelector
import com.sinannuhoglu.fooddeliveryapp.ui.components.TotalPriceCard
import com.sinannuhoglu.fooddeliveryapp.ui.home.HomeViewModel
import com.sinannuhoglu.fooddeliveryapp.ui.theme.UiConstants
import kotlinx.coroutines.launch

@Composable
fun DetailScreen(
    navController: NavController,
    foodItem: FoodItem,
    basketViewModel: BasketViewModel = hiltViewModel(),
    homeViewModel: HomeViewModel = hiltViewModel(),
    detailViewModel: DetailViewModel = hiltViewModel()
) {
    val updatedFood =
        homeViewModel.filteredFoods.collectAsState().value.find { it.id == foodItem.id }

    LaunchedEffect(Unit) {
        detailViewModel.setFoodItem(foodItem)
    }

    val item by detailViewModel.foodItem.collectAsState()
    val quantity by detailViewModel.quantity.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = UiConstants.gradientBackground)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(UiConstants.spacerHeight))
            Text(
                text = "Bir Tıkla Sofranda",
                style = MaterialTheme.typography.bodySmall.copy(fontSize = UiConstants.headerFontSize),
                color = UiConstants.mainGrayTextColor
            )
            Spacer(modifier = Modifier.height(UiConstants.spacerHeight))

            Scaffold(
                containerColor = Color.Transparent,
                snackbarHost = {
                    SnackbarHost(
                        snackbarHostState,
                        modifier = Modifier.padding(bottom = UiConstants.snackbarPadding)
                    )
                },
                topBar = {
                    IconButton(
                        onClick = { navController.popBackStack() },
                        modifier = Modifier.padding(
                            start = UiConstants.defaultPadding,
                            top = UiConstants.smallPadding
                        )
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_close),
                            contentDescription = "Kapat",
                            tint = Color.Gray
                        )
                    }
                },
                bottomBar = {
                    if (item != null) {
                        TotalPriceCard(quantity = quantity, price = item!!.price) {
                            detailViewModel.addToBasket(basketViewModel)
                            scope.launch {
                                snackbarHostState.showSnackbar("Sepete eklendi!")
                                navController.navigate("home")
                            }
                        }
                    }
                }
            ) { padding ->
                if (item == null) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(padding),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(color = UiConstants.mainButtonColor)
                    }
                } else {
                    val food = updatedFood ?: item!!
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(padding)
                            .padding(horizontal = UiConstants.defaultPadding),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.TopCenter
                        ) {
                            AsyncImage(
                                model = food.imageUrl,
                                contentDescription = food.name,
                                modifier = Modifier
                                    .size(300.dp)
                                    .padding(top = 32.dp)
                            )
                            IconButton(
                                onClick = { homeViewModel.toggleFavorite(food) },
                                modifier = Modifier
                                    .align(Alignment.TopEnd)
                                    .padding(top = UiConstants.smallPadding, end = 32.dp)
                            ) {
                                Icon(
                                    imageVector = if (food.isFavorite) Icons.Filled.Favorite else Icons.Outlined.Favorite,
                                    contentDescription = "Favori",
                                    tint = if (food.isFavorite) colorResource(id = R.color.mainColor) else Color.Gray,
                                    modifier = Modifier.size(32.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(UiConstants.spacerHeight))
                        FoodInfoSection(name = food.name)
                        Spacer(modifier = Modifier.height(UiConstants.spacerHeight))
                        QuantitySelector(
                            price = food.price,
                            quantity = quantity,
                            onDecrease = { detailViewModel.decreaseQuantity() },
                            onIncrease = { detailViewModel.increaseQuantity() }
                        )
                        Spacer(modifier = Modifier.height(80.dp))
                    }
                }
            }
        }
    }
}
